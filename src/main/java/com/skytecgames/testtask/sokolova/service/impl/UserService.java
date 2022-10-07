package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.User;
import com.skytecgames.testtask.sokolova.repository.impl.UserRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service<User> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return userRepository.getAll();
    }

    @Override
    public User getById(int id) throws SQLException {
        return userRepository.getById(id);
    }

    @Override
    public User getRandom() throws SQLException {
        return userRepository.getRandom();
    }

    @Override
    public void update(User user) throws SQLException {
        userRepository.update(user);
    }
}
