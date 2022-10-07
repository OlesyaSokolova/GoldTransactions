package com.skytecgames.testtask.sokolova.service;

import com.skytecgames.testtask.sokolova.model.TaskAssignement;
import com.skytecgames.testtask.sokolova.model.TransactionInfo;
import com.skytecgames.testtask.sokolova.model.User;
import com.skytecgames.testtask.sokolova.repository.TaskAssignementRepository;
import com.skytecgames.testtask.sokolova.repository.impl.UserRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TaskAssignementService {
    private final TaskAssignementRepository taskAssignementRepository;

    public TaskAssignementService(TaskAssignementRepository taskAssignementRepository) {
        this.taskAssignementRepository = taskAssignementRepository;
    }

    public List<TaskAssignement> getAll() throws SQLException {
        return taskAssignementRepository.getAll();
    }

    public TaskAssignement getById(int id) throws SQLException {
        return taskAssignementRepository.getById(id);
    }

    public void save(int userId, int taskId) throws SQLException {
        taskAssignementRepository.save(new TaskAssignement(userId, taskId));
    }
}
