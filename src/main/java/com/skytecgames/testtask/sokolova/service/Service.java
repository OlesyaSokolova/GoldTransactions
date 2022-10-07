package com.skytecgames.testtask.sokolova.service;

import java.sql.SQLException;
import java.util.List;

public interface Service<T> {
    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    T getRandom() throws SQLException;

    void update(T t) throws SQLException;
}
