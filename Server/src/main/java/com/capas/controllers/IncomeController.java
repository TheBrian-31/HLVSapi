package com.capas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capas.models.entities.Income;
import com.capas.service.IncomeService;

@RequestMapping("/api/income")
public class IncomeController {

	@Autowired
	private IncomeService incomeService;
	
	
	@GetMapping("/all")
	private ResponseEntity<?> findAll() {
		
		List<Income> incomes = incomeService.findAllIncomes();
		
		return new ResponseEntity<>(incomes, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	private ResponseEntity<?> findOne(){
		
		Income income = incomeService.findIncome(null);
		
		return new ResponseEntity<>(income, HttpStatus.OK);
		
	}  
	
}
