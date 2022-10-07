package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.repository.impl.TaskRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskService implements Service<Task> {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    //Этот метод не нужен, но возможно стоит оставить на будущее

    @Override
    public List<Task> getAll() throws SQLException {
        return taskRepository.getAll();
    }

    @Override
    public Task getById(int taskId) throws SQLException {
        return taskRepository.getById(taskId);
    }

    @Override
    public boolean save(Task task) {
        //taskRepository.save
        return false;
    }

    @Override
    public Task getRandom() throws SQLException {
        return taskRepository.getRandomTask();
    }
}
