package com.skytecgames.testtask.sokolova.service.db;

import com.skytecgames.testtask.sokolova.Main;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;


public class ConnectionPoolWrapper {
    private final static String DB_PROPERTIES_FILE = "/db.properties";

    private final HikariDataSource dataSource;

    public ConnectionPoolWrapper() throws IOException, SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Properties properties = new Properties();
        properties.load(Main.class.getResourceAsStream(DB_PROPERTIES_FILE));
        String url = properties.getProperty("URL");
        String login = properties.getProperty("LOGIN");
        String password = properties.getProperty("PASSWORD");

        Class.forName("com.mysql.jdbc.Driver");
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(login);
        config.setPassword(password);
        dataSource = new HikariDataSource(config);
        // connection = DriverManager.getConnection(url, login, password);
    }

    //TODO: apply statement (give it to connection pool)
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}

