package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_email", columnList = "email")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_users_email",
                        columnNames = "email"
                )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private Boolean isActive;

    @Column
    private Boolean isNewUser;

    @Column
    private String otp;

    @Column
    private LocalDateTime otpExpiry;

    @Column
    private String registrationToken;

    @Column
    private String refreshToken;

    @Column
    private LocalDateTime RefreshTokenExpiryTime;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserRole> userRoles = new ArrayList<>();
}
