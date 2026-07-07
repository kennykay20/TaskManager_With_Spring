package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.responses.TokenResponseDto;
import com.kennybowen.taskmanager.domain.entities.User;

import java.util.Map;

public interface JwtService {
    String generateAccessToken(User user, Map<String, Object> claims);
    String generateRefreshToken();
    boolean validateToken(String token);
}
