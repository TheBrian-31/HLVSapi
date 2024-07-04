package com.capas.models.entities;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
@Entity
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String hash;
	
	private Date time_passed;
	
	private Boolean state;
	
	@OneToMany(mappedBy = "token", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Income> incomes;
	
	@OneToMany(mappedBy = "token", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Tokenxpermission> tokenxpermissions;

	public Token(String hash, Date time_passed, Boolean state) {
		super();
		this.hash = hash;
		this.time_passed = time_passed;
		this.state = state;
	}
	
}
