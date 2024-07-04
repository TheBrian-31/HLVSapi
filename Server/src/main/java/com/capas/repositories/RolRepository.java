package com.capas.repositories;

import com.capas.models.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, String> {

	Rol getByRol(String string);

}
