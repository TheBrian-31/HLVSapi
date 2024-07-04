package com.capas.models.dtos;

import java.util.UUID;

import com.capas.models.entities.Token;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
}
