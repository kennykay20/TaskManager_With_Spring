package com.kennybowen.taskmanager.application.exceptions;

import com.kennybowen.taskmanager.application.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BusinessException {
    public UnauthorizedException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
