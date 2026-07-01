package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.TaskService;
import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.TaskResponseDto;
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
                        "Task List",
                        result
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> getTaskById(@PathVariable Long id) {

        var result = _taskService.getTaskById(id);

        //System.out.println("result: "+ result);
        if(result != null){
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Task retrieved successfully",
                            result
                    )
            );
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TaskResponseDto>> createTask(@RequestBody CreateTaskRequestDto requestDto) {

        var result = _taskService.createTask(requestDto);

        return ResponseEntity.status(201).body(
                new ApiResponse<>(
                        true,
                        "Task created successfully",
                        result
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TaskResponseDto>> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequestDto requestDto){

       var result = _taskService.updateTask(id, requestDto);
       if(result != null) {
           // return no content or updated message
           return ResponseEntity.ok(
                   new ApiResponse<>(
                           true,
                           "Task updated successfully",
                           result
                   )
           );
       }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteTask(@PathVariable Long id) {
        _taskService.deleteTask(id);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task deleted successfully",
                        null
                )
        );
    }
}
