package com.capas.repositories;

import com.capas.models.entities.Door;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoorRepository extends JpaRepository<Door, Integer> {

}
