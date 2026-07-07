package com.kennybowen.taskmanager.application.dtos.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterUserRequestDto(

        @NotBlank(message = "FirstName is required.")
        @Size(min = 3, max = 50, message = "FirstName must be between 3 and 50 characters")
        String firstName,

        @NotBlank(message = "LastName is required.")
        @Size(min = 3, max = 50, message = "LastName must be between 3 and 50 characters")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        String password
) {
}
