package com.capas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capas.models.entities.State;
import com.capas.service.StateService;

@RequestMapping("/api/state")
public class StateController {

	@Autowired
	private StateService stateService;
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll(){
		
		List<State> states = stateService.findAllStates();
		
		return new ResponseEntity<>(states, HttpStatus.OK);
		
	}
	
	@GetMapping("/  ")
	private ResponseEntity<?> findOne(){
		
		//TODO: complete the method
		State state = stateService.findState(null);
		
		return new ResponseEntity<>(state, HttpStatus.OK);		
	}
	
}
