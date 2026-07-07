package com.kennybowen.taskmanager.application.contracts.services;

import com.kennybowen.taskmanager.application.dtos.requests.LoginRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.RegisterUserRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    ApiResponse<UserResponseDto> registerUser(RegisterUserRequestDto requestDto);
    TokenResponseDto loginUser(LoginRequestDto requestDto);
    List<UserResponseDto> getAllUser();
    PagedResult<UserResponseDto> getAllUser(Pageable pageable);
}
