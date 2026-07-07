package com.kennybowen.taskmanager.infrastructure.persistences.repositories;

import com.kennybowen.taskmanager.domain.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
