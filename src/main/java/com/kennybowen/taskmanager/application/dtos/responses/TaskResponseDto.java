package com.kennybowen.taskmanager.application.dtos.responses;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDateTime createdAt
) {
}
