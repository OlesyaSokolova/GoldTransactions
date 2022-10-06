package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.impl.Clan;
import com.skytecgames.testtask.sokolova.model.impl.Task;
import com.skytecgames.testtask.sokolova.model.impl.User;
import com.skytecgames.testtask.sokolova.service.Service;

import java.util.ArrayList;
import java.util.List;

public class GameHost {
    
    private final Service<Clan> clanService;
    private final Service<Task> taskService;
    private final Service<User> userService;

    private List<Clan> clans;
    private List<User> users;
    
    public GameHost(Service<Clan> clanService, Service<Task> taskService, Service<User> userService) {
        this.clanService = clanService;
        this.taskService = taskService;
        this.userService = userService;
    }

    public void prepareGameConditions() {
        clans = new ArrayList<>(clanService.getAll());
        users = new ArrayList<>(userService.getAll());
        users.forEach(user -> {
            Task task = Task.generateRandomTask();
            taskService.save(task);
            user.assignTask(task);
            userService.save(user);
        });
    }

    public void start() {
       // usersAsTHreadsPool.startDoingTasks
    }
}
