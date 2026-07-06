package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
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
public class Task extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean completed = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
