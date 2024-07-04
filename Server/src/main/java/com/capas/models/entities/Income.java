package com.capas.models.entities;

import java.util.Date;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "income")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private Date date_time;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "token_id", nullable = true)
	private Token token;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "door_id", nullable = true)
	private Door door;
	
}
