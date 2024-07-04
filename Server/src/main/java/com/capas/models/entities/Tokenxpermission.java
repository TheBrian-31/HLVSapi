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
@Table(name = "tokenxpermission")
@Entity
@Data
public class Tokenxpermission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "token_id", nullable = true)
	private Token token;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "permission_id", nullable = true)
	private Permission permission;
	
}
