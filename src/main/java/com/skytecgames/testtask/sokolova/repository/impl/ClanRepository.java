package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.impl.Clan;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClanRepository implements RepositoryInterface<Clan> {

    private final String PARAMETRIZED_STATEMENT = "insert into clans(id, version, timestamp, uid, username, changeset, lat, lon, visible) " +
            "values (?,  ?,       ?,         ?,   ?,        ?,         ?,   ?,   ?)";

    private final ConnectionPoolWrapper connectionPool;

    public ClanRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPool = connectionPoolWrapper;
    }

    private Clan createClan(ResultSet resultSet) throws SQLException {
        return new Clan(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getInt("gold"));
    }

    @Override
    public List<Clan> getAll() throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             Statement statement = connection.createStatement()) {

            List<Clan> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM clans");
            while (resultSet.next()) {
                result.add(createClan(resultSet));
            }
            return result;
        }
    }

    @Override
    public Clan getById(long id) {
        return null;
    }

    public void update(Clan clan) throws SQLException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(PARAMETRIZED_STATEMENT)) {
            statement.setInt(1, clan.getGold());
            statement.execute();
        }
    }
}
