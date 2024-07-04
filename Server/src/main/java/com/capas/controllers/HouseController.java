package com.capas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.entities.House;
import com.capas.service.HouseService;

@RestController
@RequestMapping("/api/house")
public class HouseController {

	@Autowired
	private HouseService houseService;
	
	@PostMapping("/save")
	private ResponseEntity<?> save(){
		
		//TODO: Implement save Logic
		houseService.saveHouse();
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOne(){
		
		//TODO: Implement the method with credentials
		
		House house = houseService.findHouse(null);
		
		return new ResponseEntity<>(house, HttpStatus.OK);
		
	}
<<<<<<< HEAD
=======

	@GetMapping("/all")
	private ResponseEntity<?> findAll(){

		return new ResponseEntity<>(houseService.findAllHouses(), HttpStatus.OK);

	}

	@PostMapping("/add-user")
	private ResponseEntity<GeneralResponse> addUserToHouse(@RequestBody AssingUserToHouseDTO assingUserToHouseDTO){
		try {
			houseService.assignUserToHouse(assingUserToHouseDTO.getHouseId(),assingUserToHouseDTO.getUserIdentifier());
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return GeneralResponse.getResponse(HttpStatus.CONFLICT, e.getMessage());
		}
	}

	@DeleteMapping("/remove-user")
	private ResponseEntity<GeneralResponse> removeUserToHouse(@RequestBody AssingUserToHouseDTO assingUserToHouseDTO){
		try {
			
			houseService.removeUserToHouse(assingUserToHouseDTO.getHouseId(),assingUserToHouseDTO.getUserIdentifier());
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return GeneralResponse.getResponse(HttpStatus.CONFLICT, e.getMessage());
		}
	}
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
}
