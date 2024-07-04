package com.capas.models.entities;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "permission")
@Entity
public class Permission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private Date date_time_application;
<<<<<<< HEAD
	
	private Date date_time_start;
	
	private Date date_time_end;
	
	private Time days_week;
	
=======

//	private Date date_time_start;
//
//	private Date date_time_end;
//
//	private List<Date> days_week;
	
	//Entrada unica
	private Date unique_entry;
		
	//Entrada en un rango de dias
	private Date date_time_start;
	private Date date_time_end;
	    
	//Entrada solo ciertos dias
	private List<String>  days_week;

>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	@OneToMany(mappedBy = "permission", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Tokenxpermission> tokenxpermissions;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "state_id", nullable = true)
	private State state;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "house_id", nullable = true)
	private House house;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_petition_id", nullable = true)
	private User user_petition;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_permitted_id", nullable = true)
	private User user_permitted;
<<<<<<< HEAD
=======

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "visitor_id", nullable = true)
	private User visitor;

	//Constructor de Entrada Unica
	public Permission(Date date_time_application, Date unique_entry, State state, House house, User user_petition,
			User user_permitted, User visitor) {
		super();
		this.date_time_application = date_time_application;
		this.unique_entry = unique_entry;
		this.state = state;
		this.house = house;
		this.user_petition = user_petition;
		this.user_permitted = user_permitted;
		this.visitor = visitor;
	}

	//Constructor de Entrada por rango de dias
	public Permission(Date date_time_application, Date date_time_start, Date date_time_end, State state, House house,
			User user_petition, User user_permitted, User visitor) {
		super();
		this.date_time_application = date_time_application;
		this.date_time_start = date_time_start;
		this.date_time_end = date_time_end;
		this.state = state;
		this.house = house;
		this.user_petition = user_petition;
		this.user_permitted = user_permitted;
		this.visitor = visitor;
	}

	//Constructor de Entrada para dias especificos
	public Permission(Date date_time_application, List<String> days_week, State state, House house, User user_petition,
			User user_permitted, User visitor) {
		super();
		this.date_time_application = date_time_application;
		this.days_week = days_week;
		this.state = state;
		this.house = house;
		this.user_petition = user_petition;
		this.user_permitted = user_permitted;
		this.visitor = visitor;
	}
	
	
>>>>>>> 5e83cf71db9efeda9f933091b4f919be4722c460
	
}
