package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.impl.User;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.util.ArrayList;

public class UserRepository implements RepositoryInterface<User> {
    private final ConnectionPoolWrapper connectionPoolWrapper;

    public UserRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
    }

    @Override
    public ArrayList<User> getAll() {
        return null;
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public boolean save(User user) {
        return false;
    }
}
