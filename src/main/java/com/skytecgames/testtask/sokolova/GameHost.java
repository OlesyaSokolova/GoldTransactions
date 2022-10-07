package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.model.TransactionInfo;
import com.skytecgames.testtask.sokolova.model.User;
import com.skytecgames.testtask.sokolova.service.Service;
import com.skytecgames.testtask.sokolova.service.TaskAssignementService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
            user.assignTask(task);
            taskAssignementsService.save(user.getId(), task.getId());
        }
    }

    public void start() {
        List<CompletableFuture<TransactionInfo>> completableFutures = users.stream()
                        .map(user -> CompletableFuture.supplyAsync(
                                user::performTask))
                        /*.map(future -> future.thenCompose(quote ->
                                CompletableFuture.supplyAsync(() -> //create another future to be composed...
                                        Discount.applyDiscount(quote))))*/
                        .collect(Collectors.toList());

        List<TransactionInfo> transactions =  completableFutures.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
