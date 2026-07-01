package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;

import java.util.List;

public interface TaskService {

    List<TaskResponseDto> getAllTask();
    TaskResponseDto createTask(CreateTaskRequestDto requestDto);
    TaskResponseDto getTaskById(Long id);
    TaskResponseDto updateTask(Long id, UpdateTaskRequestDto requestDto);
    void deleteTask(Long id);
}
