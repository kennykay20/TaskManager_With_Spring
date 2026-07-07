package com.kennybowen.taskmanager.application.dtos.responses;

import java.time.LocalDateTime;

public record RoleResponseDto(
        Long id,
        String name,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
