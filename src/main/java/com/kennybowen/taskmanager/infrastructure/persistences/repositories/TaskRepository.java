package com.kennybowen.taskmanager.infrastructure.persistences.repositories;

import com.kennybowen.taskmanager.domain.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Method Name query
    // SELECT * FROM tasks WHERE completed = :completed
    List<Task> findAllByCompleted(Boolean completed);

    List<Task> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT t FROM Task t WHERE t.completed = :completed")
    List<Task> findTasksByCompletionStatus(@Param("completed") Boolean completed);
}
