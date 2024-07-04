package com.capas.service;

import com.capas.models.dtos.IdTokenRequestDTO;
import com.capas.models.entities.User;

public interface AuthService {

    String googleLogin(IdTokenRequestDTO IdTokenDTO);

    User verifyTokenId(String tokenId);

    User after0AuthLogin(User user);

    String signInByPassword(User user, String email);
}
