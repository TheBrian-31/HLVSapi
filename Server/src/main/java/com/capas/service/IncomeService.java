package com.capas.service;

import com.capas.models.entities.Income;

import java.util.List;

public interface IncomeService {

    public Income findIncome(String id);

    public List<Income> findAllIncomes();

    public List<Income> findIncomesByDate(String date);
}
