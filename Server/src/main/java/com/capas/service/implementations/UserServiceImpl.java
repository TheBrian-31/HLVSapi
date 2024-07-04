package com.capas.service.implementations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.capas.models.dtos.EditUserDTO;
import com.capas.models.entities.House;
import com.capas.models.entities.Rol;
import com.capas.repositories.RolRepository;
import com.capas.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.capas.models.entities.User;
import com.capas.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolRepository rolRepository;

	@Override
	public void save(String email) {
		
		User aux = userRepository.findByNameOrEmail(email, email).orElse(null);
		
		if (aux==null) {
			User user = new User();
			//TODO: recrear el usuarios
					//(null, email, null);
			
			userRepository.save(user);
		}else {
			throw new RuntimeException("Usuario ya existente");
		}
				
	}

	@Override
	public User findUserByIdentifier(String identifier) {
		return userRepository.findByDuiOrEmail(identifier, identifier);
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<House> getAllHousesByUser(UUID Id) {
		return null;
	}

	@Override
	public void assignRoleToUser(User user, String roleId) {
		//User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Rol rol = rolRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Rol not found"));

		if(rol == null) throw new RuntimeException("Rol inexistente");
		
		if(user.getRoles().contains(rol)) throw new RuntimeException("Rol already assigned");

		user.getRoles().add(rol); // Agrega el rol a la lista de roles del usuario

		userRepository.save(user); // Guarda el usuario actualizado en la base de datos
	}

	@Override
	public void deleteRoleToUser(User user, String roleId) {
		//User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Rol rol = rolRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Rol not found"));
		
		if(rol == null) throw new RuntimeException("Rol inexistente");

		if(!user.getRoles().contains(rol)) throw new RuntimeException("Rol not assigned");

		user.getRoles().remove(rol); // Agrega el rol a la lista de roles del usuario

		userRepository.save(user); // Guarda el usuario actualizado en la base de datos
	}

	@Override
	public void editUser(User user, EditUserDTO info) {
		
		//User user = getCurrentUser();
		
		//User user = userRepository.getById(info.getUserId());
		
		if (user==null) {
			throw new RuntimeException("Usuario no encontrado");
		}
		
		user.setDui(info.getDui());
		user.setName(info.getUsername());
		
		userRepository.save(user);
		
	}

	@Override
	public List<User> findAllUsersByRol() {
		
		Rol rol = rolRepository.findById("VIGI").orElse(null);
		
		return userRepository.findAllByRoles(rol);
	
	}

	@Override
    public Optional<User> getUserbyEmail(String email) {
        return Optional.ofNullable(userRepository.findOneByEmail(email));
    }

	@Override
    public void save(User user) {
        userRepository.save(user);
    }


	@Override
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
            return userRepository.findOneByEmail(username);
        }
        return null;
    }

}
