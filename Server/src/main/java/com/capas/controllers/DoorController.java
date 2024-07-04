package com.capas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.entities.Door;
import com.capas.service.DoorService;

@RestController
@RequestMapping("/api/door")
public class DoorController {

	@Autowired
	private DoorService doorService;
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(){
		
		List<Door> doors = doorService.findAllDoors();
		
		return new ResponseEntity<>(doors, HttpStatus.OK);
		
	}
	
}
