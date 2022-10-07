package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.model.TaskType;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements RepositoryInterface<Task> {

    private final ConnectionPoolWrapper connectionPool;

    public TaskRepository(ConnectionPoolWrapper connectionPool) {
        this.connectionPool = connectionPool;
    }

    private Task createTask(ResultSet resultSet) throws SQLException {
        return new Task(resultSet.getInt("id"),
                TaskType.valueOf(resultSet.getString("type")),
                resultSet.getString("description"),
                resultSet.getInt("award")
        );
    }

    @Override
    public List<Task> getAll() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<Task> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");
            while (resultSet.next()) {
                result.add(createTask(resultSet));
            }
            return result;
        }
    }

    @Override
    public Task getById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?")) {

            List<Task> result = new ArrayList<>();
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(createTask(resultSet));
            }
            return result.get(0);
        }
    }


    public Task getRandom() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<Task> result = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM tasks " +
                    "ORDER BY RAND() " +
                    "LIMIT 1");
            while (resultSet.next()) {
                result.add(createTask(resultSet));
            }
            return result.get(0);
        }
    }
}
