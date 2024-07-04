package com.capas.service;

import com.capas.models.entities.Door;

import java.util.List;

public interface DoorService {

    public Door findDoor(Integer id);

    public List<Door> findAllDoors();
}
