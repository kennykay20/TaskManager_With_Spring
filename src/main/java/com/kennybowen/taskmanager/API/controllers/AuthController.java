package com.kennybowen.taskmanager.API.controllers;

import com.kennybowen.taskmanager.application.contracts.services.UserService;
import com.kennybowen.taskmanager.application.dtos.requests.LoginRequestDto;
import com.kennybowen.taskmanager.application.dtos.requests.RegisterUserRequestDto;
import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.dtos.responses.TokenResponseDto;
import com.kennybowen.taskmanager.application.dtos.responses.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auths")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponseDto>> register(@Valid @RequestBody RegisterUserRequestDto requestDto) {
        var result = userService.registerUser(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(
                        true,
                        result.message(),
                        null,
                        null,
                        result.id()
                )
        );
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenResponseDto>> login(@Valid @RequestBody LoginRequestDto requestDto) {
        var result = userService.loginUser(requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(
                        true,
                        result.message(),
                        null,
                        result,
                        result.id().longValue()
                )
        );
    }

}
