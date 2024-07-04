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
@Table(name = "rol")
@Entity
public class Rol {

	@Id
	private Integer id;
	
	private String rol;
	
	@OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Userxrol> userxrols;
}
