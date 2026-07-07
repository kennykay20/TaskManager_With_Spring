package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.requests.CreateRoleRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.RoleResponseDto;

import java.util.List;

public interface RoleService {

    RoleResponseDto createRole(CreateRoleRequestDto requestDto);
    List<RoleResponseDto> getAllRole();
}
