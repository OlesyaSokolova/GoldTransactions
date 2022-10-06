package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.impl.Clan;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.util.ArrayList;

public class ClanRepository implements RepositoryInterface<Clan> {
    private final ConnectionPoolWrapper connectionPoolWrapper;

    public ClanRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
    }

    @Override
    public ArrayList<Clan> getAll() {
        return null;
    }

    @Override
    public Clan getById(long id) {
        return null;
    }

    @Override
    public boolean save(Clan user) {
        return false;
    }
}
