package com.kennybowen.taskmanager.application.dtos.responses;

import java.time.LocalDateTime;

public record CategoryResponseDto(
        Long categoryId,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
