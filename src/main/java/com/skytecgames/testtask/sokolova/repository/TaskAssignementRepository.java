package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.TaskAssignement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskAssignementRepository {
    private final ConnectionPoolWrapper connectionPool;

    public TaskAssignementRepository(ConnectionPoolWrapper connectionPool) {
        this.connectionPool = connectionPool;
    }

    private TaskAssignement createTaskAssignement(ResultSet resultSet) throws SQLException {
        return new TaskAssignement(resultSet.getInt("id"),
                resultSet.getInt("userid"),
                resultSet.getInt("taskid")
        );
    }

    public List<TaskAssignement> getAll() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<TaskAssignement> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM userstasks");
            while (resultSet.next()) {
                result.add(createTaskAssignement(resultSet));
            }
            return result;
        }
    }

    public TaskAssignement getById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM userstasks WHERE id = ?")) {

            List<TaskAssignement> result = new ArrayList<>();
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(createTaskAssignement(resultSet));
            }
            return result.get(0);
        }
    }

    public void save(TaskAssignement taskAssignement) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO userstasks (userid, taskid) " +
                                        "values (?,      ?)")) {

            statement.setInt(1, taskAssignement.getUserId());
            statement.setInt(2, taskAssignement.getTaskId());
            statement.execute();
        }
    }
}
