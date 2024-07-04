package com.capas.service.implementations;

import java.util.List;

import com.capas.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.entities.Rol;
import com.capas.service.RolService;

@Service
public class RolServiceImpl implements RolService{

	@Autowired
	RolRepository rolRepository;

	@Override
	public Rol findRol(String id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public List<Rol> findAllRoles() {
		return rolRepository.findAll();
	}

}
