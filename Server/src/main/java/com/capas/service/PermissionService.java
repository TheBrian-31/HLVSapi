package com.capas.service;

import com.capas.models.entities.Permission;

import java.util.List;

public interface PermissionService {

    public void savePermission();

    public void editPermission();

    public void deletePermission();

    public Permission findPermission(String id);

    public List<Permission> findAllPermissions();

}
