package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.requests.CreateCategoryRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.CategoryResponseDto;

public interface CategoryService {
    CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto);
    CategoryResponseDto findById(Long id);
}
