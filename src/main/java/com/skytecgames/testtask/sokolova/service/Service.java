package com.skytecgames.testtask.sokolova.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: this class should work with info about completed tasks
public interface Service<T> {
    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    boolean save(T t);

    T getRandom() throws SQLException;
}
