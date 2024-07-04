package com.capas.service.implementations;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.dtos.GenerateHashDTO;
import com.capas.models.entities.Permission;
import com.capas.models.entities.State;
import com.capas.models.entities.Token;
import com.capas.repositories.TokenRepository;
import com.capas.service.PermissionService;
import com.capas.service.StateService;
import com.capas.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{

	@Autowired
	private TokenRepository tokenRepository;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private StateService stateService;
	
	@Override
	public String generateToken(GenerateHashDTO info) throws Exception {
		
		UUID uuid = UUID.fromString(info.getPermissionId());
		
		Permission permission = permissionService.findPermission(uuid);
		
		State state = stateService.findState("APRO");
		if (permission==null || permission.getState() != state) {
			throw new Exception("Permiso no valido");
		}
		
		Token token = new Token();
		
		token.setTime_passed(new Date());
		token.setState(true);
		token.setHash(info.getPermissionId());
		
		tokenRepository.save(token);
		
		return token.getId().toString();
		
	}

	@Override
	public String validateToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Token findTokenById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Token> findAllTokens() {
		// TODO Auto-generated method stub
		return null;
	}

}
