package com.capas.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capas.models.entities.User;
import com.capas.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUserByIdentifier(String id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

<<<<<<< HEAD
=======
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

>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
}
