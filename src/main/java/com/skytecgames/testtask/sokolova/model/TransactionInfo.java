package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TransactionInfo {

    private final int userId;
    private final int clanId;
    private final int taskId;
    private final int goldToAdd;
}
