package com.kennybowen.taskmanager.infrastructure.persistences.repositories;

import com.kennybowen.taskmanager.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
