package com.capas.controllers;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.dtos.GenerateHashDTO;
import com.capas.models.entities.Token;
import com.capas.service.TokenService;

@RestController
@RequestMapping("/api/hash")
public class TokenController {

	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/")
	private ResponseEntity<?> findTokenById(){
		
		//TODO: Implement method
		Token token = tokenService.findTokenById(null);
		
		return new ResponseEntity<>(token, HttpStatus.OK);
		
	}
	
	@GetMapping("/generate")
	private ResponseEntity<?> generateHash(@RequestBody GenerateHashDTO info){
		
		try {
			String hash = tokenService.generateToken(info);
			return new ResponseEntity<>(hash, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage() ,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	
}
