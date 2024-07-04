package com.capas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.entities.Rol;
import com.capas.service.RolService;

@RestController
@RequestMapping("/api/rol")
public class RolController {

	@Autowired
	private RolService rolService;
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll() {
		
		List<Rol> roles = rolService.findAllRoles();
		
		return new ResponseEntity<>(roles, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOne() {
		
		Rol rol = rolService.findRol(null);
		
		return new ResponseEntity<>(rol, HttpStatus.OK);
		
	}
	
}
