package com.capas.service;

import com.capas.models.dtos.GenerateHashDTO;
import com.capas.models.entities.Token;

import java.util.List;

public interface TokenService {

    public String generateToken(GenerateHashDTO info) throws Exception;

    public String validateToken(String token);

    public Token findTokenById(String id);

    public List<Token> findAllTokens();
    
}
