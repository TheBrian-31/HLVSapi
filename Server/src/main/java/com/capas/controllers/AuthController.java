package com.capas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capas.models.dtos.IdTokenRequestDTO;
import com.capas.models.dtos.TokenDTO;
import com.capas.service.AuthService;
import com.capas.service.UserService;
import com.capas.utils.JWTTools;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    private final UserService userService;

    private final JWTTools jwt;
    
    @Autowired
    public AuthController(AuthService authService, UserService userService, JWTTools jwt) {
        this.authService = authService;
        this.userService = userService;
        this.jwt = jwt;
    }

    @PostMapping("/google")
    public ResponseEntity<?> signInWithGoogle(@ModelAttribute(value = "idToken") IdTokenRequestDTO token) {
        try {
            if (token == null || token.getIdToken() == null || token.getIdToken().split("\\.").length != 3) {
                // Token Invalid
                return new ResponseEntity<>("Token is invalid or malformed", HttpStatus.BAD_REQUEST);
            }

            String authToken = authService.googleLogin(token);
            return new ResponseEntity<>(new TokenDTO(authToken), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
