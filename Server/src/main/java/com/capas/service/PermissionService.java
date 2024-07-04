package com.capas.service;

import com.capas.models.dtos.EditPermissionDTO;
import com.capas.models.dtos.SavePermissionDTO;
import com.capas.models.entities.Permission;

import java.util.List;
import java.util.UUID;

public interface PermissionService {

    public void savePermission(SavePermissionDTO savePermissionDTO);

    public void editPermission(EditPermissionDTO editPermissionDTO);

    public void deletePermission(UUID id);

    public Permission findPermission(UUID id);

    public List<Permission> findAllPermissions();

}
