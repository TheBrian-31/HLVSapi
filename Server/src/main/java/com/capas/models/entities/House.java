package com.capas.models.entities;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
<<<<<<< HEAD

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
=======
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "house")
@Entity
<<<<<<< HEAD
=======
//@ToString(exclude = "users")
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private Integer capacity;
	
	private Integer n_door;
	
	@OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions;
<<<<<<< HEAD
	
	@OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Userxhouse> userxhouses;
=======

	@ManyToMany(mappedBy = "houses", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<User> users;

	public House(Integer n_house, Integer capacity){
		super();
		this.n_house = n_house;
		this.capacity = capacity;
	}
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
}
