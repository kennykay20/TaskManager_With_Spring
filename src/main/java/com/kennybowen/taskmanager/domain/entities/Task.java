package com.kennybowen.taskmanager.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Builder;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Getter// gives us the getter and setter
@Setter
@NoArgsConstructor // no constructor
@AllArgsConstructor //
//@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
