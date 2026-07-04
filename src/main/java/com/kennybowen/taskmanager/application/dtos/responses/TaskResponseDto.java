package com.kennybowen.taskmanager.application.dtos.responses;

import com.kennybowen.taskmanager.domain.entities.Category;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        Boolean completed,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        CategoryResponseDto category
) {
}
