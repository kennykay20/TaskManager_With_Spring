package com.kennybowen.taskmanager.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String type) {
        super(type + " not found wth id: " + id);
    }
}
