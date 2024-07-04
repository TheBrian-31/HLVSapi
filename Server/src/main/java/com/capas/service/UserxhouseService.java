package com.capas.service;

import com.capas.models.entities.House;
import com.capas.models.entities.User;

import java.util.List;

public interface UserxhouseService {

    public List<User> findUsersByHouse(String houseId);

    public House findHousesByUser(String userId);
}
