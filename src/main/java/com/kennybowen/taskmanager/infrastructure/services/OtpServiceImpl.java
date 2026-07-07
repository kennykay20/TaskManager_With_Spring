package com.kennybowen.taskmanager.infrastructure.services;

import com.kennybowen.taskmanager.application.contracts.services.OtpService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class OtpServiceImpl implements OtpService {

    @Override
    public String generateOtp() {
        return String.valueOf(
                ThreadLocalRandom.current()
                        .nextInt(111111, 1000000)
        );
    }

    @Override
    public LocalDateTime getExpiryTime() {
        return LocalDateTime.now()
                .plusMinutes(10);
    }
}
