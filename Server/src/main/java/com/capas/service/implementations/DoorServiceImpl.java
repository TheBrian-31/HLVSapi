package com.capas.service.implementations;

import java.util.List;

import com.capas.repositories.DoorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.entities.Door;
import com.capas.service.DoorService;

@Service
public class DoorServiceImpl implements DoorService {

	@Autowired
	private DoorRepository doorRepository;

	@Override
	public Door findDoor(Integer id) {
		return doorRepository.findById(id).orElse(null);
	}

	@Override
	public List<Door> findAllDoors() {
		return doorRepository.findAll();
	}

}
