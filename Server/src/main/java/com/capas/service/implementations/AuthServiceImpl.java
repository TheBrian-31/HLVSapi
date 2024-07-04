package com.capas.service.implementations;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.capas.models.dtos.IdTokenRequestDTO;
import com.capas.models.entities.User;
import com.capas.repositories.UserRepository;
import com.capas.service.AuthService;
import com.capas.service.UserService;
import com.capas.utils.JWTTools;
import com.capas.utils.PasswordUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private PasswordUtil generalUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    private GoogleIdTokenVerifier verifier;

    public AuthServiceImpl(@Value("${googleClientId}") String clientId, UserRepository userRepository,
                           JWTTools jwtTools) {
        this.userRepository = userRepository;
        this.jwtTools = jwtTools;
        NetHttpTransport transport = new NetHttpTransport();
        JsonFactory jsonFactory = new GsonFactory();
        verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
    }
    @Override
    @Transactional
    public User after0AuthLogin(User user) {
        //Checking if user exists
        Optional<User> existingUser = userService.getUserbyEmail(user.getEmail());
        if(existingUser.isEmpty())
        {
            // default password
            String defaultPassword = generalUtils.generateDefaultPassword();
            // hash password
            user.setPassword(generalUtils.hashPassword(defaultPassword));

            userService.save(user);
            return user;
        }

        existingUser.get().setName(user.getName());
        existingUser.get().setEmail(user.getEmail());
        return existingUser.get();
    }

    @Override
    public String googleLogin(IdTokenRequestDTO IdTokenDTO) {

        User user =  verifyTokenId(IdTokenDTO.getIdToken());
        if( user == null )
            return "User does not exists";


        user = after0AuthLogin(user);
        return jwtTools.createToken(user);

    }

    @Override
    public User verifyTokenId(String tokenId) {
        try {


            GoogleIdToken googleIdToken = verifier.verify(tokenId);
            if (googleIdToken == null) {
                return null;
            }
            GoogleIdToken.Payload payload = googleIdToken.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();
            User user = new User();
            user.setName(String.join(" ", firstName,lastName));
            user.setEmail(email);
            return user;
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }


    @Override
    public String signInByPassword(User user, String email) {
        return null;
    }
}
