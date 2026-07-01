package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.mapper.TaskMapper;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.domain.entities.Task;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository _taskRepository;
    private final TaskMapper taskMapper;


    @Override
    public List<TaskResponseDto> getAllTask() {
        return taskMapper.toListDto(
                _taskRepository.findAll()
        );
    }

    @Override
    public TaskResponseDto createTask(CreateTaskRequestDto requestDto) {
        Task task = new Task();
        task.setTitle(requestDto.title());
        task.setDescription(requestDto.description());
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());

        Task savedTask = _taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = _taskRepository.findById(id)
                .orElse(null);

        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, UpdateTaskRequestDto requestDto) {
        Task task = _taskRepository.findById(id)
                .orElse(null);

        if(task != null) {
            task.setTitle(requestDto.title());
            task.setDescription(requestDto.description());
            task.setCompleted(requestDto.completed());
            task.setUpdatedAt(LocalDateTime.now());


            Task updatedTask = _taskRepository.save(task);

            return taskMapper.toDto(updatedTask);
        }
        return null;
    }

    @Override
    public void deleteTask(Long id) {
        _taskRepository.deleteById(id);
    }
}
