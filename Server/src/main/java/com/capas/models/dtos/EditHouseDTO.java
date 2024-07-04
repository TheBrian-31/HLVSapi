package com.capas.models.dtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.util.UUID;

@Data
public class EditHouseDTO {
    private UUID houseId;
    private Integer capacity;
    private String userIdentifier;
}
