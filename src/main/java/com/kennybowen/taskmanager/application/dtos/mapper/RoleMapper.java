package com.kennybowen.taskmanager.application.dtos.mapper;

import com.kennybowen.taskmanager.application.dtos.responses.RoleResponseDto;
import com.kennybowen.taskmanager.domain.entities.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponseDto toDto(Role role);
    List<RoleResponseDto> toListDto(List<Role> roles);
}
