package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.impl.Clan;
import com.skytecgames.testtask.sokolova.model.impl.User;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public User getById(long id) {
        return null;
    }
}
