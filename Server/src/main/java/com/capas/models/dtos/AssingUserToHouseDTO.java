package com.capas.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Data
public class AssingUserToHouseDTO {
    @NotBlank
    private UUID houseId;
    @NotBlank
    private String userIdentifier;
}
