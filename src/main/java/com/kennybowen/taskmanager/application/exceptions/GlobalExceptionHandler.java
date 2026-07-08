package com.kennybowen.taskmanager.application.exceptions;

import com.kennybowen.taskmanager.application.dtos.responses.ApiResponse;
import com.kennybowen.taskmanager.application.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<String>> handleBusinessException(BadRequestException ex, WebRequest request) {

        return ResponseEntity.status(ex.getStatus()).body(
                new ApiResponse<>(
                        false,
                        ex.getMessage(),
                        List.of(ex.getMessage()),
                        null,
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(Exception ex, WebRequest request) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(
                        false,
                        "An unexceptional error occurred ",
                        null,
                        null,
                        null
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {

        List<String> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());



        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            new ApiResponse<>(
                    false,
                    "Validation failed",
                    errorList,
                    null,
                    null
            )
        );
    }

}
