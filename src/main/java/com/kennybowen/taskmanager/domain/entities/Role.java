package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "roles",
        indexes = {
                @Index(name = "idx_roles_name", columnList = "name")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(length = 100)
    private String description;

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserRole> userRoles = new ArrayList<>();
}
