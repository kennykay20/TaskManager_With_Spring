package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.ClaimService;
import com.kennybowen.taskmanager.domain.entities.User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ClaimServiceImpl implements ClaimService {
    @Override
    public Map<String, Object> getUserClaims(User user) {
        return Map.of();
    }
}
