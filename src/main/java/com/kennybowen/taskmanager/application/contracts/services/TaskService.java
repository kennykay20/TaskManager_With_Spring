package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiListPageResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.PagedResult;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.domain.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAllTask();
    PagedResult<TaskResponseDto> getAllTask(Pageable pageable);
    TaskResponseDto createTask(CreateTaskRequestDto requestDto);
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto updateTask(Long id, UpdateTaskRequestDto requestDto);
    boolean deleteTask(Long id);
    List<TaskResponseDto> getTasksByCompletionStatus(Boolean status);
    List<TaskResponseDto> searchTasksByTitle(String title);
}
