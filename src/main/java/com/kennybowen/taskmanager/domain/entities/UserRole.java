package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(
        name = "user_roles",
        indexes = {
                @Index(name = "idx_user_roles_user_id", columnList = "user_id"),
                @Index(name = "idx_user_roles_role_id", columnList = "role_id")
        },
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_user_role",
                        columnNames = {"user_id", "role_id"}
                )
        }
)
public class UserRole extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
