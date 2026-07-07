package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.domain.entities.User;

import java.util.Map;

public interface ClaimService {

    Map<String, Object> getUserClaims(User user);
}
