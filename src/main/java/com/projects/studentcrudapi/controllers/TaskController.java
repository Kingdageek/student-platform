package com.projects.studentcrudapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projects.studentcrudapi.dtos.TaskDto;
import com.projects.studentcrudapi.entities.Task;
import com.projects.studentcrudapi.entities.TaskStatus;
import com.projects.studentcrudapi.repositories.TaskRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createTask(@RequestBody TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDescription(taskDto.getDescription());

        Task response = this.repository.save(newTask);
        return new ResponseEntity<>(response.getId(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.noContent().build();
        }
        Task existingTask = optionalTask.get();

        return ResponseEntity.ok().body(existingTask.toDto());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@RequestBody TaskDto taskDto, @PathVariable("id") long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        Task existingTask = optionalTask.get();
        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        TaskStatus.valueOf(taskDto.getStatus());
        if (taskDto.getStatus() != null) {
            if (TaskStatus.get(taskDto.getStatus()) == null) {
                return ResponseEntity.badRequest()
                        .body("Available statuses are: CREATED, APPROVED, REJECTED, BLOCKED, DONE.");
            }
            existingTask.setTaskStatus(TaskStatus.valueOf(taskDto.getStatus()));
        }

        this.repository.save(existingTask);
        return ResponseEntity.ok().body("");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") long id) {
        Optional<Task> optionalTask = this.repository.findById(id);
        if (!optionalTask.isPresent()) {
            return ResponseEntity.noContent().build();
        }

        Task existingTask = optionalTask.get();
        this.repository.delete(existingTask);
        return ResponseEntity.ok().body("");
    }

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<TaskDto> tasks = this.repository.findAll()
                .stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(tasks);
    }

}
