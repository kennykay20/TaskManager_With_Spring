package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiListPageResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.PagedResult;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @GetMapping("/page")
    public ResponseEntity<ApiListPageResponseDto<List<TaskResponseDto>>> getAllTaskByPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ){

        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        PagedResult<TaskResponseDto> result = _taskService.getAllTask(pageable);


        return ResponseEntity.ok(
                new ApiListPageResponseDto<>(
                        true,
                        "Retrieved Tasks in a pagination",
                        null,
                        result.pageNumber(),
                        result.pageSize(),
                        result.items().size(),
                        result.totalPages(),
                        (int) result.totalCount(),
                        result.hasNext(),
                        result.hasPrevious(),
                        result.items()
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

    // Filtering by title without pagination
    @GetMapping("/search/title")
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

    // Filtering by title, completed in pagination list
    @GetMapping("/search")
    public ResponseEntity<ApiListPageResponseDto<List<TaskResponseDto>>> searchTasks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Boolean completed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDir
    ){


        Sort sort = sortDir.equalsIgnoreCase("ASC") ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        PagedResult<TaskResponseDto> result;

        if(title != null && completed != null) {
            result = _taskService.searchTasksByTitleAndCompletion(title, completed, pageable);
        }
        else if (title != null){
            // filter by title only
            result = _taskService.searchTasksByTitle(title, pageable);
        }
        else if(completed != null){
            result = _taskService.getTasksByCompletion(completed, pageable);
        }
        else {
            result = _taskService.getAllTask(pageable);
        }

        return ResponseEntity.ok(
                new ApiListPageResponseDto<>(
                        true,
                        "Retrieved Tasks in a pagination",
                        null,
                        result.pageNumber(),
                        result.pageSize(),
                        result.items().size(),
                        result.totalPages(),
                        (int) result.totalCount(),
                        result.hasNext(),
                        result.hasPrevious(),
                        result.items()
                )
        );
    }
}
