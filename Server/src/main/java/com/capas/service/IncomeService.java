package com.capas.service;

import com.capas.models.entities.Income;

import java.util.List;
import java.util.UUID;

public interface IncomeService {

    public Income findIncome(UUID id);

    public List<Income> findAllIncomes();

    public List<Income> findIncomesByDate(String date);
}
