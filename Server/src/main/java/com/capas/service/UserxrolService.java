package com.capas.service;

import com.capas.models.entities.User;

import java.util.List;

public interface UserxrolService {


    public List<User> findUsersByRol(String rolId);

    public User findRolbyUser(String userId);
}
