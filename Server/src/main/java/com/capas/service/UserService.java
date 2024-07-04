package com.capas.service;

import com.capas.models.dtos.EditUserDTO;
import com.capas.models.entities.House;
import com.capas.models.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    public void save(String email);
    
    public void save(User user);

    public User findUserByIdentifier(String id);

    public void deleteUser(User user);

    public User updateUser(User user);
    
    public void editUser(User user, EditUserDTO info);
    
    Optional<User> getUserbyEmail(String email);

    public List<User> findAllUsers();

    public List<House> getAllHousesByUser(UUID Id);

    void assignRoleToUser(User user, String roleId);

    void deleteRoleToUser(User user, String roleId);

	public List<User> findAllUsersByRol();
	
	public User getCurrentUser();

}
