package com.kennybowen.taskmanager.application.exceptions;

import com.kennybowen.taskmanager.application.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundExceptionWithEmail extends BusinessException {
    public NotFoundExceptionWithEmail(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
