package com.kennybowen.taskmanager.application.dtos.mapper;

import com.kennybowen.taskmanager.application.dtos.requests.RegisterUserRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.UserResponseDto;
import com.kennybowen.taskmanager.domain.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDto toDto(User user);
    User toUser(RegisterUserRequestDto requestDto);
    List<UserResponseDto> toListDto(List<User> users);
}
