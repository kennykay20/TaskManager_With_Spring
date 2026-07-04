package com.kennybowen.taskmanager.application.dtos.mapper;

import com.kennybowen.taskmanager.application.dtos.responses.CategoryResponseDto;
import com.kennybowen.taskmanager.domain.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "categoryId", source = "id")
    CategoryResponseDto toDto(Category category);

    List<CategoryResponseDto> toListDto(List<Category> category);

    Category toCategory(CategoryResponseDto categoryDto);
}
