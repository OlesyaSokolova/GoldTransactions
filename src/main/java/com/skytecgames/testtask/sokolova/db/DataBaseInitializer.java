package com.skytecgames.testtask.sokolova.db;

import com.skytecgames.testtask.sokolova.model.impl.Task;
import com.skytecgames.testtask.sokolova.model.impl.User;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
public class DataBaseInitializer {

    private static final int TASKS_NUMBER = 1000;
    private static final int USERS_NUMBER = 200;
    private final static int INSERT_LIMIT = 100;

    private static final String DDL_FILE_PATH = "src/main/resources/dbcreate.sql";
    private static final String CLANS_INFO_FILE_PATH = "src/main/resources/clans.sql";

    private final ConnectionPoolWrapper connectionPool;

    public DataBaseInitializer(ConnectionPoolWrapper connectionPool) {
        this.connectionPool = connectionPool;
    }

    private String readSqlFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename),
                        StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining(" "));
        }
    }

    private void executeSqlFromFile(String filename) throws IOException, SQLException {
        String sql = readSqlFromFile(filename);
        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private void generateTasks() throws SQLException {
        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(Task.PARAMETRIZED_STATEMEND)) {
            for (int i = 0; i < TASKS_NUMBER; i++) {

                Task task = Task.generateRandomTask();
                statement.setString(1, task.getType().toString());
                statement.setString(2, task.getDescription());
                statement.setInt(3, task.getAward());
                statement.addBatch();

                //statement.addBatch(task.getInsertStatement());
                if(i % INSERT_LIMIT == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
        }
    }

    private void generateUsers() throws SQLException {

        try(Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(User.PARAMETRIZED_STATEMENT)) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM clans");
            resultSet.next();

            int clansNumber = resultSet.getInt(1);
            for (int i = 0; i < USERS_NUMBER; i++) {

                User user = User.generateRandomUser();
                user.assignToClan(ThreadLocalRandom.current().nextInt(0, clansNumber));

                statement.setString(1, user.getName());
                statement.setString(2, user.getEmail());
                statement.setInt(3, user.getClanId());
                statement.setInt(4, user.getGold());
                statement.addBatch();
                //statement.addBatch(user.getInsertStatement());
                if(i % INSERT_LIMIT == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
        }
    }

    public void initDB() throws IOException, SQLException {
        executeSqlFromFile(DDL_FILE_PATH);
        executeSqlFromFile(CLANS_INFO_FILE_PATH);
        generateUsers();
        generateTasks();
    }
}
