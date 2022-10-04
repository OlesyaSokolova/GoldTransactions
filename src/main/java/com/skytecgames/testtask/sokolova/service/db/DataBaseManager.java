package com.skytecgames.testtask.sokolova.service.db;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@Getter
public class DataBaseManager {

    private final static String DDL_FILE_PATH = "src/main/resources/dbcreate.sql";
    private final ConnectionPoolWrapper connectionPool;

    public DataBaseManager() throws SQLException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.connectionPool = new ConnectionPoolWrapper();
    }

    public void initDB() throws IOException, SQLException {
        String sql = getSQL(DDL_FILE_PATH);
       // String sql = "SELECT * FROM users;" + "SELECT * from clans;";
        Statement statement = connectionPool.getConnection().createStatement();
        statement.executeUpdate(sql);
    }

    private String getSQL(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(filename),
                        StandardCharsets.UTF_8))) {
            return br.lines().collect(Collectors.joining(" "));
        }
    }

    /*public void clearDB() throws SQLException {
        Statement statement = connection.createStatement();
        String query = "truncate table tags cascade;" +
                "truncate table nodes cascade;";
        statement.execute(query);
        connection.commit();
    }*/
}
