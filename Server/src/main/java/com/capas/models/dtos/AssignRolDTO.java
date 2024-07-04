package com.capas.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AssignRolDTO {
    @NotBlank
    private String userEmail;
    @NotBlank
    private String roleCode;
}

