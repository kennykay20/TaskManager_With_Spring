package com.kennybowen.taskmanager.application.dtos.responses;

public record ApiResponse<T>(
        Boolean success,
        String message,
        String[] errors,
        T data
) {
}
