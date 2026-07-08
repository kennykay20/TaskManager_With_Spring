package com.kennybowen.taskmanager.application.exceptions;

import com.kennybowen.taskmanager.application.exceptions.common.BusinessException;
import org.springframework.http.HttpStatus;

public class NotFoundExceptionById extends BusinessException {
    public NotFoundExceptionById(Long id, String type) {
        super(type + " not found wth id: " + id, HttpStatus.NOT_FOUND);
    }
}
