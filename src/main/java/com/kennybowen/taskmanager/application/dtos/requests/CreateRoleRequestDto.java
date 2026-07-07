package com.kennybowen.taskmanager.application.dtos.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateRoleRequestDto(

        @NotNull(message = "Name is required")
        @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
        String name,

        @Size(min = 3, max = 100, message = "Description must be between 3 and 100 characters")
        String description
) {
}
