package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Getter// gives us the getter and setter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {

    @Column(length = 50)
    private String firstName;

    @Column(length = 50)
    private String lastName;

    @Column
    private String email;

    @Column
    private Integer age;

    @ManyToMany(mappedBy = "authors") // author is the reverse while course entity is the owner of the relationship
    private List<Course> courses;
}
