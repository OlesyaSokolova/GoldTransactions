package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.model.User;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements RepositoryInterface<User> {

    private final ConnectionPoolWrapper connectionPool;

    public UserRepository(ConnectionPoolWrapper connectionPool) {
        this.connectionPool = connectionPool;
    }

    private User createUser(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getInt("clanid"),
                resultSet.getInt("gold")
                );
    }

    @Override
    public List<User> getAll() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<User> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                result.add(createUser(resultSet));
            }
            return result;
        }
    }

    @Override
    public User getById(int id) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {

            List<User> result = new ArrayList<>();
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(createUser(resultSet));
            }
            return result.get(0);
        }
    }

    @Override
    public User getRandom() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<User> result = new ArrayList<>();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users " +
                    "ORDER BY RAND() " +
                    "LIMIT 1");
            while (resultSet.next()) {
                result.add(createUser(resultSet));
            }
            return result.get(0);
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE users SET clanid = ?, name = ?, email = ?, gold = ? WHERE id = ?")) {

            statement.setInt(1, user.getClanId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setInt(4, user.getGold());
            statement.execute();
        }
    }
}
