package com.capas.controllers;

import com.capas.models.dtos.AssingUserToHouseDTO;
import com.capas.models.dtos.EditHouseDTO;
import com.capas.models.dtos.GeneralResponse;
import com.capas.models.dtos.SaveHouseDTO;
import com.capas.models.entities.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capas.models.entities.House;
import com.capas.service.HouseService;

import java.util.UUID;

@RestController
@RequestMapping("/api/house")
public class HouseController {



	@Autowired
	private HouseService houseService;
	
	@PostMapping("/save")
	private ResponseEntity<?> save(@RequestBody SaveHouseDTO saveHouseDTO){

		houseService.saveHouse(saveHouseDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

	@DeleteMapping("/delete")
	private ResponseEntity<?> delete(@RequestParam(name = "houseId") UUID houseId){

		houseService.deleteHouse(houseId);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PatchMapping("/edit")
	private ResponseEntity<?> edit(@RequestBody EditHouseDTO editHouseDTO){

		houseService.editHouse(editHouseDTO);

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOne(@RequestParam(name = "houseId") UUID houseId){

		
		House house = houseService.findHouse(houseId);
		
		return new ResponseEntity<>(house, HttpStatus.OK);
		
	}

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
	
}
