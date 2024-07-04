package com.capas.models.entities;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

//@AllArgsConstructor
//@NoArgsConstructor
@Table(name = "userxhouse")
@Entity
@Data
public class Userxhouse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "house_id", nullable = true)
	private House house;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = true)
	private User user;
	
}
