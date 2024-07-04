package com.capas.models.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
	private String id;
	
	private String state;
	
	@OneToMany(mappedBy = "state", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions;
	
}
