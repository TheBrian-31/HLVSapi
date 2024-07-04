package com.capas.models.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.management.relation.Role;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@NoArgsConstructor
@Table(name = "user")
@Entity
//@ToString(exclude = "houses")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(unique = true, nullable = true, name = "dui")
	private String dui;
	
	@Column(unique = true, nullable = false, name = "email")
	private String email;
	
	@Column(nullable = false, name = "name")
	private String name;
	
	@Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Transient
    @JsonIgnore
    private String  password;
	
	@OneToMany(mappedBy = "user_petition", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions_user_petition;
	
	@OneToMany(mappedBy = "user_permitted", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Permission> permissions_user_permitted;

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
	
	@Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority>authorities = new ArrayList<>();
        authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getId())).collect(Collectors.toList());
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    
    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    
	public User(UUID id, String dui, String email, String name) {
		super();
		this.id = id;
		this.dui = dui;
		this.email = email;
		this.name = name;
	}
	
}
