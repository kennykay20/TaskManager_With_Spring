package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.RoleService;
import com.kennybowen.taskmanager.application.dtos.requests.CreateRoleRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoleResponseDto>>> getRoles(){

        var result = roleService.getAllRole();

        return !result.isEmpty() ? ResponseEntity.ok().body(
                new ApiResponse<>(
                        true,
                        "Retrieved role list",
                        null,
                        result,
                        null
                )
        ) : ResponseEntity.badRequest().build();
    }
    @PostMapping
    public ResponseEntity<ApiResponse<RoleResponseDto>> addRole(@RequestBody CreateRoleRequestDto requestDto){
        var result = roleService.createRole(requestDto);

        return result.id() != null ? ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        "Role added successfully",
                        null,
                        result,
                        result.id()
                )
        ) : ResponseEntity.badRequest().build();
    }
}
