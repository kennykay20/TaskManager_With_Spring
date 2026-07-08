package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "courses")
@Getter// gives us the getter and setter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 400)
    private String description;

    @ManyToMany // course is the owner of the relationship
    @JoinTable(
        name = "authors_courses",
        joinColumns = {
            @JoinColumn(name = "course_id")
        },
        inverseJoinColumns = {
            @JoinColumn(name = "author_id")
        }
    )
    private List<Author> authors;

    @OneToMany(mappedBy = "course")
    private List<Section> sections;
}
