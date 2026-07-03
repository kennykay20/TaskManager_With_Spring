package com.kennybowen.taskmanager.application.dtos.mapper;

import com.kennybowen.taskmanager.application.dtos.responses.ApiListPageResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.domain.entities.Task;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDto toDto(Task task);

    List<TaskResponseDto> toListDto(List<Task> tasks);
}
