package com.projects.studentcrudapi.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projects.studentcrudapi.dtos.TaskDto;

import java.io.Serializable;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.CREATED;

    public Task(String title) {
        this.title = title;
    }

    public Task() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTaskStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public TaskDto toDto() {
        return new TaskDto(String.valueOf(id), title, description, status.name());
    }
}
