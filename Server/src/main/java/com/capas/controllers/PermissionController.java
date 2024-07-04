package com.capas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.entities.Permission;
import com.capas.service.PermissionService;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll(){
		
		List<Permission> permissions = permissionService.findAllPermissions();
		
		return new ResponseEntity<>(permissions, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/")
	private ResponseEntity<?> findOne(){
		
		//TODO: Implement method
		Permission permission = permissionService.findPermission(null);
		
		return new ResponseEntity<>(permission, HttpStatus.OK);
		
	}
	
	@PostMapping("/save")
	private ResponseEntity<?> save(){
		
<<<<<<< HEAD
		//TODO: Implement the method
		
		permissionService.savePermission();
=======
		//Change the method to define what type of entry is has been solicituded
		
		permissionService.savePermission(savePermissionDTO);
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
<<<<<<< HEAD
	
=======

	@PatchMapping("/edit")
	private ResponseEntity<?> edit(@RequestBody EditPermissionDTO editPermissionDTO){

		permissionService.editPermission(editPermissionDTO);

		return new ResponseEntity<>(HttpStatus.OK);

	}

	@DeleteMapping("/delete")
	private ResponseEntity<?> delete(@RequestParam(name = "id") UUID id){

		UUID idUuid = UUID.fromString(id.toString());
		
		permissionService.deletePermission(idUuid);

		return new ResponseEntity<>(HttpStatus.OK);

	}
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
}
