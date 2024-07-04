package com.capas.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import com.capas.models.dtos.EditHouseDTO;
import com.capas.models.dtos.SaveHouseDTO;
import com.capas.models.entities.User;
import com.capas.repositories.HouseRepository;
import com.capas.repositories.RolRepository;
import com.capas.repositories.UserRepository;
import com.capas.service.UserService;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.entities.House;
import com.capas.models.entities.Rol;
import com.capas.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseRepository houseRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RolRepository rolRepository;


	@Override
	@Transactional
	public void saveHouse(SaveHouseDTO info) {
		House newHouse = new House(
				info.getN_house(),
				info.getCapacity()
		);
		houseRepository.save(newHouse);

		assignUserToHouse(newHouse.getId(), info.getUserIdentifier());

		Rol rolAdmin = rolRepository.findById("REEN").orElse(null); //Nunca deberia ser nulo porque estamos seguros que el Rol con id REEN existe

		User user = userRepository.findByDuiOrEmail(info.getUserIdentifier(), info.getUserIdentifier());
		
		if (user==null) {
			throw new RuntimeException("Usuario no encontrado");
		}
		
		user.getRoles().add(rolAdmin);

		userRepository.save(user);


	}

	@Override
	public void editHouse(EditHouseDTO info) {
		House house = houseRepository.findById(info.getHouseId()).orElse(null);
		User user = userRepository.findByDuiOrEmail(info.getUserIdentifier(), info.getUserIdentifier()); //.orElse(null);

		if (house == null) throw new RuntimeException("La casa no existe");
		if (user == null) throw new RuntimeException("Usuario no encontrado");
		if (!house.getUsers().contains(user)) throw new RuntimeException("El usuario no pertenece a la casa");

		Rol rolAdmin = rolRepository.findById("REEN").orElse(null);
		Rol rolVisitante = rolRepository.findById("VISI").orElse(null);


		// Encuentra al usuario administrador anterior
		User previousAdmin = house.getUsers().stream()
				.filter(u -> u.getRoles().contains(rolAdmin))
				.findFirst()
				.orElse(null);

		if (previousAdmin != null) {
			// Quita el rol de administrador al usuario anterior
			previousAdmin.getRoles().remove(rolAdmin);

			// Asegúrate de que el usuario anterior tenga el rol de visitante
			if (!previousAdmin.getRoles().contains(rolVisitante)) {
				previousAdmin.getRoles().add(rolVisitante);
			}

			// Guarda el usuario anterior actualizado en la base de datos
			userRepository.save(previousAdmin);
		}


		house.setCapacity(info.getCapacity());

		if (rolAdmin==null) { //No deberia porque suceder porque el rol lo creamos manualmente pero por si acaso
			throw new RuntimeException("Usuario no encontrado");
		}

		if (user.getRoles().contains(rolAdmin)) { //Si ya contiene el rol de encargado entonces solamente guarda al usuario y la casa con sus otros valores
			userRepository.save(user);
			houseRepository.save(house);
		}else {
			user.getRoles().add(rolAdmin); //De lo contrario asigana el nuevo rol al usuario porque ahora tiene una casa y guarda

			userRepository.save(user);
			houseRepository.save(house);
		}

	}

	@Override
	public void deleteHouse(UUID houseId) {
		House house = houseRepository.findById(houseId).orElse(null);
		if(house == null){
			throw new RuntimeException("Casa no encontrada");
		}else {

			List<User> users = house.getUsers(); //Tomo todos los usuarios de esa casa

			Rol rolResidente = rolRepository.findById("RESI").orElse(null);//Nunca deberia ser null
			Rol rolResiEncargado = rolRepository.findById("REEN").orElse(null);
			Rol rolVisitante = rolRepository.findById("VISI").orElse(null);

			users.forEach(user -> { //Recorro 1 a 1 todos los usuarios

				user.getHouses().remove(house); //Elimino la casa de cada uno de los usuarios

				if (user.getHouses().isEmpty()) { //Si el usuario no tiene mas casas entonces le quito los roles de Residente y Residente Encargado

					user.getRoles().remove(rolResiEncargado);
					user.getRoles().remove(rolResidente);
					if (!user.getRoles().contains(rolVisitante)) {
						user.getRoles().add(rolVisitante);
					} //Le dejo solo rol de visitante
				}else {
					// Si el usuario tiene más casas, comprobamos si sigue siendo encargado de alguna de ellas
					boolean isStillManager = user.getHouses().stream()
							.anyMatch(h -> h.getUsers().contains(user) && user.getRoles().contains(rolResiEncargado));

					if (!isStillManager) {
						// Si ya no es encargado de ninguna casa, le quitamos el rol de encargado
						user.getRoles().remove(rolResiEncargado);
					}
				}

				userRepository.save(user);

			});

			houseRepository.delete(house);

		}
		
		
		

//		Optional<User> admin = userRepository.findByRoles(house.getUsers(), "ADMR");
//
//		if (houseRepository.findAllByUsers(admin.get()).isEmpty()) {
//			userService.deleteRoleToUser(admin.get().getId(), "ADMR");
//		}

	}


	@Override
	public House findHouse(UUID id) {
		return houseRepository.findById(id).orElseThrow(() -> new RuntimeException("House not found"));
	}


	@Override
	public List<House> findAllHouses() {
		return houseRepository.findAll();
	}

	@Override
	public List<House> findAllHousesByUser(UUID userId) {
		User user = userRepository.findById(userId).orElse(null);
		
		if (user!=null) {
			return houseRepository.findAllByUsers(user);
		}
		
		return null;
	}


	@Override
	public void assignUserToHouse(UUID houseId, String identifier) {
		User user = userRepository.findByDuiOrEmail(identifier, identifier);
		
		if (user==null) {
			throw new RuntimeException("Usuario no encontrado");
		}
		
		House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));

		if(house.getUsers() == null) {
			house.setUsers(new ArrayList<>());
		}
		if(house.getUsers().contains(user)) throw new RuntimeException("User already assigned");
		if(house.getUsers().size() + 1 > house.getCapacity()) throw new RuntimeException("Se ha superado la capacidad de la casa");

		house.getUsers().add(user);
		user.getHouses().add(house);

		Rol residente = rolRepository.findById("RESI").orElseThrow(() -> new RuntimeException("Rol not found"));

		user.getRoles().add(residente);

		userRepository.save(user);

		houseRepository.save(house);
	}

	@Override
	public void removeUserToHouse(UUID houseId, String identifier) {
		User user = userRepository.findByDuiOrEmail(identifier, identifier);
		
		if (user==null) {
			throw new RuntimeException("Usuario no encontrado");
		}
		
		House house = houseRepository.findById(houseId).orElseThrow(() -> new RuntimeException("House not found"));
		Rol admin = rolRepository.findById("REEN").orElseThrow(() -> new RuntimeException("Rol not found"));

		if (!house.getUsers().contains(user)) throw new RuntimeException("User is not assigned to this house");
		if (user.getRoles().contains(admin)) throw new RuntimeException("User is the house manager");

		Rol residente = rolRepository.findById("RESI").orElseThrow(() -> new RuntimeException("Rol not found"));

		user.getRoles().remove(residente);

		user.getHouses().remove(house);
		house.getUsers().remove(user);

		userRepository.save(user);

		houseRepository.save(house);
	}

}
