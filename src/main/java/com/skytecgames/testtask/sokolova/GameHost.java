package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.*;
import com.skytecgames.testtask.sokolova.service.Service;
import com.skytecgames.testtask.sokolova.service.TaskAssignementService;
import lombok.extern.log4j.Log4j2;

import java.sql.SQLException;
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
        List<CompletableFuture<DetailedTransactionInfo>> completedTasks = users.stream()
                        .map(user ->
                                CompletableFuture.supplyAsync(user::performTask))
                        .map(future ->
                                future.thenApply(this::processTransactionResults))
                        .collect(Collectors.toList());

        //можно сохранить их в файл или как-то ещё обработать
        List<DetailedTransactionInfo> transactionInfos  = completedTasks.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    private synchronized DetailedTransactionInfo processTransactionResults(TransactionInfo transactionInfo) {
        int clanId = transactionInfo.getClanId();
        transactionInfo.setGoldBefore(clans[clanId].getGold());
        clans[clanId].updateGold(transactionInfo.getGoldDelta());
        try {
            clanService.update(clans[clanId]);
            DetailedTransactionInfo detailedTransactionInfo = getDetailedInfo(transactionInfo);

            //Информация о транзакция пишется в логи!
            log.info(detailedTransactionInfo);


            return detailedTransactionInfo;
        } catch (SQLException e) {
            log.error("Error while extracting detailed info about transaction. Brief info:\n " +
                    "User id: " + transactionInfo.getUserId() + "\n" +
                    "Clan id: " + transactionInfo.getClanId() + "\n" +
                    "Gold added: " + transactionInfo.getGoldDelta() + "\n" +
                    "Error medssage: " + e.getMessage());
            return null;
        }
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
