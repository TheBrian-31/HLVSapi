package com.capas.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capas.models.entities.House;
import com.capas.service.HouseService;

@Service
public class HouseServiceImpl implements HouseService {

	@Override
	public void saveHouse() {
		// TODO Auto-generated method stub
		
	}

	@Override
<<<<<<< HEAD
	public void editHouse() {
		// TODO Auto-generated method stub
		
=======
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

>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	}

	@Override
	public House findHouse(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<House> findAllHouses() {
		// TODO Auto-generated method stub
		return null;
	}

}
