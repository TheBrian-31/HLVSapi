package com.capas.service;

import com.capas.models.entities.House;

import java.util.List;

public interface HouseService {

    public void saveHouse();

    public void editHouse();

    public House findHouse(String id);

    public List<House> findAllHouses();

}
