package com.kennybowen.taskmanager.application.dtos.responses;

import java.util.List;

public record PagedResult<T>(
        List<T> items,
        int pageNumber,
        int pageSize,
        int totalPages,
        long totalCount,
        boolean hasNext,
        boolean hasPrevious
) {
}
