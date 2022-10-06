package com.skytecgames.testtask.sokolova.service;

import java.util.ArrayList;

//TODO: this class should work with info about completed tasks
public interface Service<T> {
    ArrayList<T> getAll();

    T getById(long id);

    boolean save(T t);
}
