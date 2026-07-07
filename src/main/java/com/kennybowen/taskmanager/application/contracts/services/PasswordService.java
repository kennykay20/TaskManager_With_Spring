package com.kennybowen.taskmanager.application.contracts.services;

public interface PasswordService {
    String hashPassword(String password);
    boolean verifyPassword(String password, String passwordHash);
}
