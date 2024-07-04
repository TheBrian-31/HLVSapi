package com.capas.service.implementations;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.entities.Income;
import com.capas.repositories.IncomeRepository;
import com.capas.service.IncomeService;

@Service
public class IncomeServiceImpl implements IncomeService{

	@Autowired
	private IncomeRepository incomeRepository;

	@Override
	public Income findIncome(UUID id) {
		return incomeRepository.findById(id).orElse(null);
	}

	@Override
	public List<Income> findAllIncomes() {
		return incomeRepository.findAll();
	}

	@Override
	public List<Income> findIncomesByDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}

}
