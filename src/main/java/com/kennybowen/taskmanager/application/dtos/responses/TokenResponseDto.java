package com.kennybowen.taskmanager.application.dtos.responses;

import java.util.List;

public record TokenResponseDto(
        Integer id,
        boolean success,
        String message,
        List<String> errors,
        String accessToken,
        String refreshToken
) {
}
