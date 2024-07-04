package com.capas.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capas.models.entities.State;
import com.capas.repositories.StateRepository;
import com.capas.service.StateService;

@Service
public class StateServiceImpl implements StateService{

	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public State findState(String id) {
		return stateRepository.findById(id).orElse(null);
	}

	@Override
	public List<State> findAllStates() {
		// TODO Auto-generated method stub
		return null;
	}

}
