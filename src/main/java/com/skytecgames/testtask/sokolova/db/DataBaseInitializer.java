package com.skytecgames.testtask.sokolova.db;

import com.skytecgames.testtask.sokolova.model.impl.Task;
import com.skytecgames.testtask.sokolova.model.impl.User;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Getter
public class DataBaseInitializer {

    private static final int TASKS_NUMBER = 1000;
    private static final int USERS_NUMBER = 1000;
    private final static int INSERT_LIMIT = 100;

    private static final String DDL_FILE_PATH = "src/main/resources/dbcreate.sql";
    private static final String CLANS_INFO_FILE_PATH = "src/main/resources/clans.sql";

    private final ConnectionPoolWrapper connectionPool;

    //TODO: pass ConnectionPool from MainController - GameController,
    // который будет все организовывать и передавать в репозитории это коннекшн пул
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

    //todo: refactor to another class - TaskGenerator
    private void generateTasks() throws SQLException {
        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            for (int i = 0; i < TASKS_NUMBER; i++) {
                Task task = Task.generateRandomTask();
                statement.addBatch(task.getInsertStatement());
                if(i % INSERT_LIMIT == 0) {
                    statement.executeBatch();
                }
            }
            statement.executeBatch();
        }
    }


    private void generateUsers() throws SQLException {

        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM clans");
            resultSet.next();

            int clansNumber = resultSet.getInt(1);
            for (int i = 0; i < USERS_NUMBER; i++) {

                User user = User.generateRandomUser();
                user.assignToClan(ThreadLocalRandom.current().nextInt(0, clansNumber));

                statement.addBatch(user.getInsertStatement());
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
        //generateClans();
        //executeSqlFromFile(USERS_INFO_FILE_PATH);
        generateUsers();
        //generateTasks();
        //TODO: NO NEED USERS TABLE! THEY ARE THREADS, GENERATE THEM
        //addUsers

    }

   /* private ResultSet executeStringQuery(String query) throws SQLException {
        try(Connection connection = connectionPool.getConnection();
            Statement statement = connection.createStatement()) {
            return statement.executeQuery(query);
        }
    }*/
    /*public void clearDB() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "truncate table tags cascade;" +
                "truncate table nodes cascade;";
        statement.execute(query);
        connection.commit();
    }*/
}
