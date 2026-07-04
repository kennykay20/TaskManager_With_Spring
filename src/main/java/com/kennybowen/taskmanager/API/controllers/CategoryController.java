package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.CategoryService;
import com.kennybowen.taskmanager.application.dtos.requests.CreateCategoryRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.CategoryResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<CategoryResponseDto>> findById(@PathVariable Long id) {
        var result = categoryService.findById(id);

        return result != null ? ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Category retrieved successfully",
                        null,
                        result
                )
        ) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponseDto>> createTask(@Valid @RequestBody CreateCategoryRequestDto requestDto) {

        var result = categoryService.createCategory(requestDto);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(
                        true,
                        "Category created successfully",
                        null,
                        result
                )
        );
    }

}
