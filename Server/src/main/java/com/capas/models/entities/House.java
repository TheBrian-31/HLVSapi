package com.capas.models.entities;

import java.util.List;
import java.util.UUID;

import com.capas.service.UserService;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "house")
@Entity
//@ToString(exclude = "users")
public class House {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private Integer capacity;

	@Column(unique = true)
	private Integer n_house;
	
	@OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions;

	@ManyToMany(mappedBy = "houses", fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<User> users;

	public House(Integer n_house, Integer capacity){
		super();
		this.n_house = n_house;
		this.capacity = capacity;
	}
	
}
