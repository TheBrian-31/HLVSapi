package com.capas.controllers;

import java.util.List;
import java.util.UUID;

import com.capas.models.dtos.EditPermissionDTO;
import com.capas.models.dtos.SavePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	private ResponseEntity<?> save(@RequestBody SavePermissionDTO savePermissionDTO){
		
		//Change the method to define what type of entry is has been solicituded
		
		permissionService.savePermission(savePermissionDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}

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
	
}
