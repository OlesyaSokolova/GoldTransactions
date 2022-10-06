package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.model.impl.User;

import java.util.ArrayList;

public interface RepositoryInterface <T> {

    ArrayList<T> getAll();

    T getById(long id);

    boolean save(T user);
}
