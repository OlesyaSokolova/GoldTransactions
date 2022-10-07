package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class TaskAssignement {
    private int id;
    private int userId;
    private int taskId;
    private LocalDateTime startTime;
    private LocalDateTime completeTime;

    public TaskAssignement(int userId, int taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public int getUserId() {
        return userId;
    }

    public int getTaskId() {
        return taskId;
    }
}
