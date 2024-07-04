package com.capas.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "state")
@Entity
public class State {

	@Id
	private Integer id;
	
	private String state;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions;
	
}
