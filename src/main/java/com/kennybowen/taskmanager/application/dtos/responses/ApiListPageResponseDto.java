package com.kennybowen.taskmanager.application.dtos.responses;

import java.util.List;

public record ApiListPageResponseDto<T>(
        Boolean Success,
        String Message,
        List<String> Errors,
        Integer PageNumber,
        Integer PageSize,
        Integer Count,
        Integer TotalPages,
        Integer TotalCount,
        Boolean hasNext,
        Boolean hasPrevious,
        T Data
) {
}
