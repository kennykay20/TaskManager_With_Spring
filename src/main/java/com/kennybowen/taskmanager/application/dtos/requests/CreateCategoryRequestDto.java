package com.kennybowen.taskmanager.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequestDto(
        @NotBlank(message = "name is required")
        @Size(min = 3, max = 100, message = "name can be between 3 and 100 characters")
        String name,

        String description
) {
}
