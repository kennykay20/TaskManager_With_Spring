package com.kennybowen.taskmanager.application.dtos.responses;

import java.util.List;

public record ApiResponse<T>(
        Boolean success,
        String message,
        List<String> errors,
        T data
) {
}
