package com.capas.service;

import com.capas.models.entities.User;

import java.util.List;

public interface UserService {

    public User save(User user);

    public User findUserByIdentifier(String id);

    public void deleteUser(User user);

    public User updateUser(User user);

    public List<User> findAllUsers();


}
