package com.projects.studentcrudapi.entities;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TaskStatus {
    CREATED, APPROVED, REJECTED, BLOCKED, DONE;

    private static final Map<String, TaskStatus> STATUS_MAP;

    static {
        STATUS_MAP = Stream.of(TaskStatus.values())
            .collect(Collectors.toMap(TaskStatus::name, Function.identity()));
    }

    public static TaskStatus get(String status) {
        return STATUS_MAP.get(status.toUpperCase().trim()); 
    }
}
