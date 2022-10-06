package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.impl.Task;
import com.skytecgames.testtask.sokolova.repository.TaskRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.util.ArrayList;

public class TaskService implements Service<Task> {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public ArrayList<Task> getAll() {
        return null;
    }

    @Override
    public Task getById(long taskId) {
        return null;
    }

    @Override
    public boolean save(Task task) {
        return false;
    }
}
