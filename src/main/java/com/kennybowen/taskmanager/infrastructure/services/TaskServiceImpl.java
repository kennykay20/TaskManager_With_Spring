package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.mapper.TaskMapper;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.application.exceptions.NotFoundException;
import com.kennybowen.taskmanager.domain.entities.Task;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
        task.setCompleted(requestDto.completed() != null ? requestDto.completed() : false);
        //task.setCreatedAt(LocalDateTime.now());

        Task savedTask = _taskRepository.save(task);
        return taskMapper.toDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = _taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Task"));

        return taskMapper.toDto(task);
    }

    @Override
    public TaskResponseDto updateTask(Long id, UpdateTaskRequestDto requestDto) {
        Task task = _taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id, "Task"));

        task.setTitle(requestDto.title() != null ? requestDto.title() : task.getTitle());
        task.setDescription(requestDto.description() != null ? requestDto.description() : task.getDescription());
        task.setCompleted(requestDto.completed() != null ? requestDto.completed() : task.getCompleted());
        task.setUpdatedAt(LocalDateTime.now());

        Task updatedTask = _taskRepository.save(task);

        return taskMapper.toDto(updatedTask);
    }

    @Override
    public boolean deleteTask(Long id) {
        return _taskRepository.findById(id)
                .map(task -> {
                    _taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public List<TaskResponseDto> getTasksByCompletionStatus(Boolean status) {
        var result = _taskRepository.findAllByCompleted(status);
        return taskMapper.toListDto(result);
    }

    @Override
    public List<TaskResponseDto> searchTasksByTitle(String title) {
        var result = _taskRepository.findByTitleContainingIgnoreCase(title);
        return taskMapper.toListDto(result);
    }
}
