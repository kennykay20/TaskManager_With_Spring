package com.kennybowen.taskmanager.application.contracts.services;

import java.time.LocalDateTime;

public interface OtpService {
    String generateOtp();
    LocalDateTime getExpiryTime();
}
