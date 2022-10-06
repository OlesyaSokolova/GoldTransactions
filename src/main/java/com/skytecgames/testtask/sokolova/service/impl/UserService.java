package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.impl.User;
import com.skytecgames.testtask.sokolova.repository.impl.UserRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.util.ArrayList;

public class UserService implements Service<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
