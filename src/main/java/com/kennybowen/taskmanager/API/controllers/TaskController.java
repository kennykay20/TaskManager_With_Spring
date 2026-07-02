package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import com.kennybowen.taskmanager.application.exceptions.NotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService _taskService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getAllTask(){

        var result = _taskService.getAllTask();
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Retrieved Tasks",
                        null,
                        result
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(@PathVariable Long id) {

        var result = _taskService.getTaskById(id);

        return result != null ? ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task retrieved successfully",
                        null,
                        result
                )
        ) : ResponseEntity.notFound().build();


    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(@Valid @RequestBody CreateTaskRequestDto requestDto) {

        var result = _taskService.createTask(requestDto);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(
                        true,
                        "Task created successfully",
                        null,
                        result
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequestDto requestDto){

       var result = _taskService.updateTask(id, requestDto);

       return result != null ? ResponseEntity.ok(
               new ApiResponse<>(
                       true,
                       "Task updated successfully",
                       null,
                       result
               )
       ): ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long id) {
        return _taskService.deleteTask(id) ?
        ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task deleted successfully",
                        null,
                        null
                )
        ) : ResponseEntity.notFound().build();
    }

    @GetMapping("/completed/{status}")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> getTasksByCompletions(@PathVariable Boolean status) {
        var result = _taskService.getTasksByCompletionStatus(status);
        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Retrieved List of completed task",
                        null,
                        result
                )
        );
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<TaskResponseDto>>> searchTasksByTitle(@RequestParam String title) {

        var result = _taskService.searchTasksByTitle(title);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Retrieved list of task by title",
                        null,
                        result
                )
        );
    }
}
