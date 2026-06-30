package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.dtos.requests.CreateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.UpdateTaskRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.domain.entities.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private Long nextId = 1L;


    @GetMapping
    public ResponseEntity<ApiResponse<List<Task>>> getAllTask(){

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task List",
                        tasks
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> getTaskById(@PathVariable Long id) {
        Task result = tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        result != null ? true : false,
                        result != null ? "Task" : "no task",
                        result
                )
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Task>> createTask(@RequestBody CreateTaskRequestDto requestDto) {

        Task task = new Task();

        task.setId(nextId++);
        task.setTitle(requestDto.title());
        task.setDescription(requestDto.description());
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());

        tasks.add(task);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Task created successfully",
                        task
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Task>> updateTask(@PathVariable Long id, @RequestBody UpdateTaskRequestDto requestDto){
        System.out.println("Inside the update task controller");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("Inside the for loop");
            System.out.println("i : " + i + ", and request Id: " + requestDto.id());
            Task task = tasks.get(i);
            System.out.println("Task: " + task);
            if(task.getId().equals(id)) {
                System.out.println("Inside the if check for getId");
                task.setTitle(requestDto.title());
                task.setDescription(requestDto.description());
                task.setUpdatedAt(LocalDateTime.now());
                tasks.set(i, task);
                System.out.println("After update and set");
                return ResponseEntity.ok(
                        new ApiResponse<>(
                                true,
                                "Update Task successfully",
                                task
                        )
                );
            }
        }

        return ResponseEntity.ok(
          new ApiResponse<>(
                  false,
                  "Error occur",
                  null
          )
        );
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
