package com.capas.models.dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class EditPermissionDTO {
    private UUID permissionId;
    private String newStateId;
}
