package com.kennybowen.taskmanager.application.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateTaskRequestDto(
        @NotBlank(message = "Title is required")
        @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
        String title,

        //@NotBlank(message = "Description is required")
        @Size(min = 3, max = 500, message = "Description can be up to 500 characters")
        String description,
        Boolean completed,
        Long categoryId
)
        {
}
