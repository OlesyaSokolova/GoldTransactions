package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TaskAssignement {
    private int id;
    private int userId;
    private int taskId;

    public TaskAssignement(int userId, int taskId) {
        this.userId = userId;
        this.taskId = taskId;
    }

    public int getUserId() {
        return userId;
    }

    public int getTaskId() {
        return taskId;
    }
}
