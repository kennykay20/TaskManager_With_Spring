package com.kennybowen.taskmanager.domain.entities;

import com.kennybowen.taskmanager.domain.entities.CommonEntity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sections")
@Getter// gives us the getter and setter
@Setter
@NoArgsConstructor // no constructor
@AllArgsConstructor //
public class Section extends BaseEntity {

    @Column
    private String name;

    @Column
    private String sectionOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
