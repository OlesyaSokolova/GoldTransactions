package com.skytecgames.testtask.sokolova.model;

 /*отслеживание разных транзакций начисления золота,
 чтобы служба поддержки смогла идентифицировать:
         когда
         по какой причине в казне изменилось количества золота,
         сколько было,
         сколько стало.*/

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TransactionInfo {

    private final int userTaskId; //-> clanid by userId, completiontime by userTask
    private final int goldBefore;
    private final int goldAfter;

    /*public getDetailedInfo() {
        return "Gold was changed in clan " + getClanNameById(userTaskId.getClanId())
                + " by user " + getUserNameById(userTaskId.getUserId()) + "."
                + "Gold before: " + goldBefore + ";\n"
                + "Gold after: " + goldAfter + "\n;"
                + "Task was completed at: " + userTaskId.getCompletedAt();

    }*/
}
