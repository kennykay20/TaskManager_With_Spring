package com.kennybowen.taskmanager.application.dtos.responses;

import java.time.LocalDateTime;

public record UserResponseDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        //String password,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
