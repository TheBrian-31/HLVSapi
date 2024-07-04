package com.capas.service;

import com.capas.models.entities.Rol;

import java.util.List;

public interface RolService {

    public Rol findRol(String id);

    public List<Rol> findAllRoles();
}
