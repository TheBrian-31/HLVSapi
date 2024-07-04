package com.capas.service;

import com.capas.models.entities.State;

import java.util.List;

public interface StateService {

    public State findState(String id);

    public List<State> findAllStates();

}
