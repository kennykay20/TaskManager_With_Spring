package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.CategoryService;
import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.mapper.CategoryMapper;
import com.kennybowen.taskmanager.application.dtos.mapper.TaskMapper;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.CategoryResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.PagedResult;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.application.exceptions.NotFoundException;
import com.kennybowen.taskmanager.domain.entities.Category;
import com.kennybowen.taskmanager.domain.entities.Task;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.CategoryRepository;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository _taskRepository;
    private final TaskMapper taskMapper;
    private final CategoryRepository _categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<TaskResponseDto> getAllTask() {
        return taskMapper.toListDto(
                _taskRepository.findAll()
        );
    }

    @Override
    public PagedResult<TaskResponseDto> getAllTask(Pageable pageable) {
        Page<Task> page = _taskRepository.findAll(pageable);
        return new PagedResult<>(
                page.getContent()
                        .stream()
                        .map(taskMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    @Override
    public TaskResponseDto createTask(CreateTaskRequestDto requestDto) {

        Category category = null;
        if(requestDto != null && requestDto.categoryId() != null)
        {
            category = _categoryRepository.findById(
                            requestDto.categoryId())
                    .orElseThrow(() ->
                            new NotFoundException(requestDto.categoryId(), "Category"));
        }
        Task task = new Task();
        task.setTitle(requestDto.title());
        task.setDescription(requestDto.description());
        task.setCompleted(requestDto.completed() != null ? requestDto.completed() : false);
        task.setCategory(category);

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

    @Override
    public PagedResult<TaskResponseDto> searchTasksByTitleAndCompletion(String title, Boolean completed, Pageable pageable) {

        Page<Task> page = _taskRepository.findAllByTitleContainingAndCompleted(title, completed, pageable);
        return new PagedResult<>(
                page.getContent()
                        .stream()
                        .map(taskMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    @Override
    public PagedResult<TaskResponseDto> searchTasksByTitle(String title, Pageable pageable) {
        Page<Task> page = _taskRepository.findByTitleContainingIgnoreCase(title, pageable);
        return new PagedResult<>(
                page.getContent()
                        .stream()
                        .map(taskMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }

    @Override
    public PagedResult<TaskResponseDto> getTasksByCompletion(Boolean completed, Pageable pageable) {
        Page<Task> page = _taskRepository.findByCompleted(completed, pageable);
        return new PagedResult<>(
                page.getContent()
                        .stream()
                        .map(taskMapper::toDto)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
