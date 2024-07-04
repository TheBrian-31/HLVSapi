package com.capas.repositories;

import com.capas.models.entities.House;
import com.capas.models.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HouseRepository extends JpaRepository<House, UUID> {

    List<House> findAllByUsers(User user);
}
