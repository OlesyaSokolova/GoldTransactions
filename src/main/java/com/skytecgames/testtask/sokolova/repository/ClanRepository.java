package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;

public class ClanRepository {
    private final ConnectionPoolWrapper connectionPoolWrapper;

    public ClanRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
    }
}
