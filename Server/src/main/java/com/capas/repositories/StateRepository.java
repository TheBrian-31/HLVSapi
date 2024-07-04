package com.capas.repositories;

import com.capas.models.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StateRepository extends JpaRepository<State, String> {

}
