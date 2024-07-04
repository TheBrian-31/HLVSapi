package com.capas.controllers;

import java.util.List;

<<<<<<< HEAD
=======
import com.capas.models.dtos.AssignRolDTO;
import com.capas.models.dtos.EditUserDTO;
import com.capas.models.dtos.GeneralResponse;
import com.capas.models.dtos.UserDTO;

>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.entities.User;
import com.capas.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
<<<<<<< HEAD
	private ResponseEntity<?> save(){
		
		User user = new User();
		
		//TODO: Implement method
		
		userService.save(user);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
		
=======
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
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	}
	
	@DeleteMapping("/delete")
	private ResponseEntity<?> delete(){
		
		User user = userService.findUserByIdentifier(null);
		
		userService.deleteUser(user);
		
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll(){
		
		List<User> users = userService.findAllUsers();
		
		return new ResponseEntity<>(users, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOneByIdentifier(){
		
		User user = userService.findUserByIdentifier(null);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
		
	}
<<<<<<< HEAD
=======

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

>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
	
	
}
