package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.repository.TaskRepository;
import com.skytecgames.testtask.sokolova.service.TaskService;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
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
