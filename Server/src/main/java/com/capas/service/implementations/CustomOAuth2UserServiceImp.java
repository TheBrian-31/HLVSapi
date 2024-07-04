package com.capas.service.implementations;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.capas.models.entities.CustomOAuth2User;
import com.capas.service.CustomOAuth2UserService;

@Service
public class CustomOAuth2UserServiceImp extends DefaultOAuth2UserService implements CustomOAuth2UserService  {
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        return new CustomOAuth2User(user);
    }
}