package com.projects.studentcrudapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.studentcrudapi.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
