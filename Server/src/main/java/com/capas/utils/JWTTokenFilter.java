package com.capas.utils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import com.capas.models.entities.User;
import com.capas.repositories.UserRepository;

@Component
public class JWTTokenFilter extends OncePerRequestFilter {

    private final JWTTools jwtTools;
    private final UserRepository userRepository;

    @Autowired
    public JWTTokenFilter(JWTTools jwtTools, UserRepository userRepository) {
        this.jwtTools = jwtTools;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");

        // Check if the request is to the base path ("/")
        if (!request.getRequestURI().equals("/")) {
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring(7).trim();
                if (!token.isEmpty()) {
                    try {
                        String username = jwtTools.getUsernameFrom(token);

                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            User user = userRepository.findOneByEmail(username);

                            if (user != null) {
                                UsernamePasswordAuthenticationToken authToken
                                        = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                                SecurityContextHolder.getContext().setAuthentication(authToken);
                            }
                        }
                    } catch (MalformedJwtException e) {
                        System.out.println("Malformed JWT: " + e.getMessage());
                    } catch (ExpiredJwtException e) {
                        System.out.println("Expired JWT: " + e.getMessage());
                    } catch (JwtException e) {
                        System.out.println("JWT Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("JWT Token is empty");
                }
            } else {
                System.out.println("Authorization header not found or does not have 'Bearer' prefix");
            }
        }

        filterChain.doFilter(request, response);
    }

}
