package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.CategoryService;
import com.kennybowen.taskmanager.application.dtos.mapper.CategoryMapper;
import com.kennybowen.taskmanager.application.dtos.requests.CreateCategoryRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.CategoryResponseDto;
import com.kennybowen.taskmanager.application.exceptions.NotFoundException;
import com.kennybowen.taskmanager.domain.entities.Category;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponseDto createCategory(CreateCategoryRequestDto requestDto) {
        Category category = new Category();
        category.setName(requestDto.name());
        category.setDescription(requestDto.description());

        var savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    @Override
    public CategoryResponseDto findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Category"));

        return categoryMapper.toDto(category);
    }
}
