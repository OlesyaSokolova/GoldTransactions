package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;

public class UserRepository {
    private final ConnectionPoolWrapper connectionPoolWrapper;

    public UserRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
    }
}
