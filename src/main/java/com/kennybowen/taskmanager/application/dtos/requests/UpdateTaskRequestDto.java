package com.kennybowen.taskmanager.application.dtos.requests;

import java.time.LocalDateTime;

public record UpdateTaskRequestDto(Long id, String title, String description, Boolean completed) {
}
