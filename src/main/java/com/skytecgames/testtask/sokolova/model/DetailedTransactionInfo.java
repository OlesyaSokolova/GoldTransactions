package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/*отслеживание разных транзакций начисления золота,
чтобы служба поддержки смогла идентифицировать:
        когда
        по какой причине в казне изменилось количества золота,
        сколько было,
        сколько стало.*/

@AllArgsConstructor
public class DetailedTransactionInfo {
    private final Task task;
    private final User user;
    private final String clanName;
    private final int goldBefore;
    private final int goldDelta;
    private final LocalDateTime startTime;
    private final LocalDateTime completeTime;

    //todo: complete time to string
    @Override
    public String toString() {
        return "Gold was changed in clan " + clanName
                + " by user " + user.getName() + " (" + user.getEmail() + ")."
                + "Clan's gold before: " + goldBefore + ";\n"
                + "Clan's gold after: " + (goldBefore + goldDelta) + ";\n"
                + "User started to complete the task at: " + startTime + ";\n"
                + "Task was completed at: " + completeTime + ".\n";
    }
}