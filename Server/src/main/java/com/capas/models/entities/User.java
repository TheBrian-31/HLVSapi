package com.capas.models.entities;

import java.util.List;
import java.util.UUID;

<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
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
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
@Entity
<<<<<<< HEAD
public class User {
=======
//@ToString(exclude = "houses")
public class User implements UserDetails{
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String dui;
	
	private String email;
	
	private String name;
	
	@OneToMany(mappedBy = "user_petition", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions_user_petition;
	
	@OneToMany(mappedBy = "user_permitted", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions_user_permitted;
<<<<<<< HEAD
=======

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "UserxRole",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
	)
	private List<Rol> roles;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "UserxHouse",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "house_id")
	)
	@JsonBackReference
	@JsonIdentityReference
	private List<House> houses;
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Userxhouse> userxhouses;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Userxrol> userxrols;
	
}
