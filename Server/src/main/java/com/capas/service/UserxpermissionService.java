package com.capas.service;

import com.capas.models.entities.Permission;
import com.capas.models.entities.User;

import java.util.List;

public interface UserxpermissionService {

    public User findUsersByPermission(String permisionId);

    public List<Permission> findPermissionsByUser(String userId);


}
