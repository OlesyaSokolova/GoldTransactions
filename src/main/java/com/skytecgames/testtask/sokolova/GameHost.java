package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.model.User;
import com.skytecgames.testtask.sokolova.service.Service;
import com.skytecgames.testtask.sokolova.service.TaskAssignementService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameHost {
    
    private final Service<Clan> clanService;
    private final Service<Task> taskService;
    private final Service<User> userService;
    private final TaskAssignementService taskAssignementsService;
    //private final TaskComletionTracker taskComletionTracker;

    private List<Clan> clans;
    private List<User> users;
    
    public GameHost(Service<Clan> clanService, Service<Task> taskService, Service<User> userService, TaskAssignementService taskAssignementService) {
        this.clanService = clanService;
        this.taskService = taskService;
        this.userService = userService;
        this.taskAssignementsService = taskAssignementService;
    }

    public void prepareGameConditions() throws SQLException {
        clans = new ArrayList<>(clanService.getAll());
        users = new ArrayList<>(userService.getAll());
       for (User user: users) {
            Task task = taskService.getRandom();
            taskService.save(task);
            user.assignTask(task);
            userService.save(user);
           // taskAssignementsService.save(user.getId(), task.getId());
        }
    }

    public void start() {
        //users.forEach(User::performTask);
    }
}
