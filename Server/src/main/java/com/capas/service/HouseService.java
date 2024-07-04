package com.capas.service;

import com.capas.models.dtos.EditHouseDTO;
import com.capas.models.dtos.SaveHouseDTO;
import com.capas.models.entities.House;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HouseService {

    public void saveHouse(SaveHouseDTO info);

    public void editHouse(EditHouseDTO info);

    public void deleteHouse(UUID houseId);

    public House findHouse(UUID id);

    public List<House> findAllHouses();

    public List<House> findAllHousesByUser(UUID UserId);

    void assignUserToHouse(UUID houseCode, String identifier);

    void removeUserToHouse(UUID houseCode, String identifier);

}
