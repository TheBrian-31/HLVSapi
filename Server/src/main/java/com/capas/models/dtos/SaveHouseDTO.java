package com.capas.models.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SaveHouseDTO {
    private Integer n_house;
    private Integer capacity;
    private String userIdentifier;

}
