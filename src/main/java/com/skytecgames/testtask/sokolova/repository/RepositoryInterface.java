package com.skytecgames.testtask.sokolova.repository;

import java.sql.SQLException;
import java.util.List;

public interface RepositoryInterface <T> {

    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    T getRandom() throws SQLException;

   // boolean save(T user) throws SQLException;
}
