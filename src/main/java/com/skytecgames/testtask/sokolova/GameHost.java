package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.*;
import com.skytecgames.testtask.sokolova.service.Service;
import com.skytecgames.testtask.sokolova.service.TaskAssignementService;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Log4j2
public class GameHost {
    
    private final Service<Clan> clanService;
    private final Service<Task> taskService;
    private final Service<User> userService;
    private final TaskAssignementService taskAssignementsService;
    //private final TaskComletionTracker taskComletionTracker;

    private Clan[] clans;
    private List<User> users;
    
    public GameHost(Service<Clan> clanService, Service<Task> taskService, Service<User> userService, TaskAssignementService taskAssignementService) {
        this.clanService = clanService;
        this.taskService = taskService;
        this.userService = userService;
        this.taskAssignementsService = taskAssignementService;
    }

    public void prepareGameConditions() throws SQLException {
        clans = clanService.getAll().toArray(Clan[]::new);
        users = new ArrayList<>(userService.getAll());
       for (User user: users) {
            Task task = taskService.getRandom();
            user.assignTask(task);
            taskAssignementsService.save(user.getId(), task.getId());
        }
    }

    public void start() {
        List<CompletableFuture<TransactionInfo>> completedTasks = users.stream()
                        .map(user -> CompletableFuture.supplyAsync(
                                user::performTask))
                        .map(future -> future.thenApply(transactionInfo -> {
                                transactionInfo.setCompleteTime(LocalDateTime.now());
                                return getDetailedTransactionInfo(transactionInfo);
                                }))
                        .collect(Collectors.toList());

        //do somethimg with result: - save to db, for example or write to file
        //completedTasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private synchronized TransactionInfo getDetailedTransactionInfo(TransactionInfo transactionInfo) {
        try {
            DetailedTransactionInfo detailedInfo = getDetailedInfo(transactionInfo);
        } catch (SQLException e) {
            //log.error: error while retrieving info about user
            e.printStackTrace();
        }
        return clans[transactionInfo.getClanId()].updateGold(transactionInfo.getGoldDelta());
    }

   private DetailedTransactionInfo getDetailedInfo(TransactionInfo transactionInfo) throws SQLException {
        return new DetailedTransactionInfo(
                taskService.getById(transactionInfo.getTaskId()),
                userService.getById(transactionInfo.getUserId()),
                clanService.getById(transactionInfo.getClanId()).getName(),
                transactionInfo.getGoldBefore(),
                transactionInfo.getGoldDelta(),
                transactionInfo.getStartTime(),
                transactionInfo.getCompleteTime()
                );
    }
}
