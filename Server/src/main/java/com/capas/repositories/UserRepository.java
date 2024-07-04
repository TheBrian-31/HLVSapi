package com.capas.repositories;

import com.capas.models.entities.House;
import com.capas.models.entities.Rol;
import com.capas.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByNameOrEmail(String name, String email);

	List<User> findAllByRoles(Rol rol);

    //Optional<User> findByRole(List<User> users, String rolId);
	
	User findOneByEmail(String email);
	
	User findByDuiOrEmail(String dui, String email);

}
