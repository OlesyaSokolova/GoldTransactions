package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TransactionInfo {

    private final int userId;
    private final int clanId;
    private final int taskId;
    private int goldBefore;
    private int goldDelta;
    private LocalDateTime startTime;
    private LocalDateTime completeTime;


    public TransactionInfo(int userId, int clanId, int taskId) {
        this.userId = userId;
        this.clanId = clanId;
        this.taskId = taskId;
    }

    public void setCompleteTime(LocalDateTime completeTime) {
        this.completeTime = completeTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setGoldBefore(int goldBefore) {
        this.goldBefore = goldBefore;
    }

    public void setGoldDelta(int goldDelta) {
        this.goldDelta = goldDelta;
    }
}
