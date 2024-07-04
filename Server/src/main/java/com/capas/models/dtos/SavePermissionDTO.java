package com.capas.models.dtos;

import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.Null;


@Data
public class SavePermissionDTO {
	//Entrada unica
	@Null
	private Date unique_entry;
	
	//Entrada en un rango de dias
	@Null
    private Date date_time_start;
    @Null
	private Date date_time_end;
    
    //Entrada solo ciertos dias
    @Null
    private List<String>  days_week;
    
    private UUID house_id;
    //private UUID user_petition_id;
    private String user_permitted_id;
    private String visitor_id;
}
