package com.capas.controllers;

import java.util.List;

import com.capas.models.dtos.AssignRolDTO;
import com.capas.models.dtos.EditUserDTO;
import com.capas.models.dtos.GeneralResponse;
import com.capas.models.dtos.UserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capas.models.entities.User;
import com.capas.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	//Ruta obsoleta por el Login de AuthController
	@PostMapping("/save")
	private ResponseEntity<?> save(@RequestParam("email") String email){
		try {
			userService.save(email);
			return new ResponseEntity<>("Usuario Creado con Exito", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/edit")
	private ResponseEntity<?> editUser(@RequestBody EditUserDTO info){
		try {
			User user = userService.getCurrentUser();
			if (user==null) {
				return new ResponseEntity<>("Usuario No encontrado", HttpStatus.NOT_FOUND);
			}
			
			userService.editUser(user, info);
			return new ResponseEntity<>("Usuario Completado", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/show")
	private ResponseEntity<?> showUser(@RequestParam("email") String email){
		try {
			User user = userService.findUserByIdentifier(email);
			
			if (user!=null) {
				return new ResponseEntity<>(user, HttpStatus.OK);
			}
			return new ResponseEntity<>("Usuario NO encontrado", HttpStatus.NOT_FOUND);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/delete")
	private ResponseEntity<?> delete(){
		
		User user = userService.getCurrentUser();
		
		userService.deleteUser(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll(){
		
		List<User> users = userService.findAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
		
	}
	
	@GetMapping("/all/vigilantes")
	private ResponseEntity<?> findAllVigilantes(){
		
		List<User> users = userService.findAllUsersByRol();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOneByIdentifier(){
		
		User user = userService.getCurrentUser();
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}

	@PostMapping("/config/user-role")
	public ResponseEntity<GeneralResponse> assignRoleToUser(@RequestBody AssignRolDTO assignRoleDTO) {
		try {
			
			User user = userService.findUserByIdentifier(assignRoleDTO.getUserEmail());
			
			if (user==null) {
				return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "El usuario deseado no existe");
			}
			
			userService.assignRoleToUser(user, assignRoleDTO.getRoleCode());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return GeneralResponse.getResponse(HttpStatus.CONFLICT, e.getMessage());
		}
	}

	@DeleteMapping("/config/user-role")
	public ResponseEntity<?> deleteRole(@RequestBody AssignRolDTO assignRoleDTO){

		try {
			
			User user = userService.findUserByIdentifier(assignRoleDTO.getUserEmail());
			
			if (user==null) {
				return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "El usuario deseado no existe");
			}
			
			userService.deleteRoleToUser(user, assignRoleDTO.getRoleCode());
			return new ResponseEntity<>(HttpStatus.OK);
		}catch (Exception e){
			return GeneralResponse.getResponse(HttpStatus.CONFLICT, e.getMessage());
		}

	}

	
	
	
}
