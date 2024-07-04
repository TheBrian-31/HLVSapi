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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "door")
@Entity
public class Door {

	@Id
	private Integer id;
	
	private String name;
	
	@OneToMany(mappedBy = "door", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Income> incomes;
	
}
