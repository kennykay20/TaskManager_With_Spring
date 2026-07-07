package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.ClaimService;
import com.kennybowen.taskmanager.application.contracts.services.JwtService;
import com.kennybowen.taskmanager.domain.entities.User;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtService {
    private final UserRepository userRepository;
    private final ClaimService claimService;

    @Override
    public String generateAccessToken(User user, Map<String, Object> claims) {
        var userId = user.getId();
        var result = userRepository.findById(userId)
                .orElse(null);

        if(result == null) {
            return null;
        }

        var claim = claimService.getUserClaims(user);

        return "";
    }

    @Override
    public boolean validateToken(String token) {
        return false;
    }

    @Override
    public String generateRefreshToken() {
        return null;
    }
}
