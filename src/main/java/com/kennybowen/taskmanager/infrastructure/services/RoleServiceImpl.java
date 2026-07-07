package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.RoleService;
import com.kennybowen.taskmanager.application.dtos.mapper.RoleMapper;
import com.kennybowen.taskmanager.application.dtos.requests.CreateRoleRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.RoleResponseDto;
import com.kennybowen.taskmanager.domain.entities.Role;
import com.kennybowen.taskmanager.infrastructure.persistences.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository _roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponseDto createRole(CreateRoleRequestDto requestDto) {
        Role role = new Role();
        role.setName(requestDto.name());
        role.setDescription(requestDto.description());

        var savedRole = _roleRepository.save(role);

        return roleMapper.toDto(savedRole);
    }

    @Override
    public List<RoleResponseDto> getAllRole() {
        var roles = _roleRepository.findAll();
        return roleMapper.toListDto(roles);
    }
}
