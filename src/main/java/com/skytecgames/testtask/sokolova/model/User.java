package com.skytecgames.testtask.sokolova.model;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

@Getter
public class User {

    private static final int NAME_LENGTH = 5;
    private static final String EMAIL_POSTFIX = "@mail.com";
    private static final int INITIAL_GOLD = 30;
    private static final int DIFFICULTY_COEFFICIENT = 1000;
    private static final int CLAN_CONTRIBUTION_PERCENTAGE = 80;

    public static final String PARAMETRIZED_STATEMENT = "INSERT INTO users (name, email, clanid, gold) " +
                                                                   "values (?,    ?,     ?,      ?   )";
    private int id;
    private final String name;
    private final String email;
    private int clanId;
    private int gold = INITIAL_GOLD;
    private Task task;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(int id, String name, String email, int clanid, int gold) {
        this.id = id;
        this.name = name;
        this.email= email;
        this.clanId = clanid;
        this.gold = gold;
    }


   /* @Override
    public String getInsertStatement() {
        return "INSERT INTO users (name, email, clanid, gold) " +
                "VALUES (" +
                "'" + name + "'" + ", " +
                "'" + email + "'" + ", " +
                clanId + ", " +
                gold + ")";
    }*/

    public static User generateRandomInstance() {
        return new User(
                RandomStringUtils.randomAlphabetic(NAME_LENGTH),
                RandomStringUtils.randomAlphabetic(NAME_LENGTH) + EMAIL_POSTFIX
        );
    }

    public void assignToClan(int clanId) {
        this.clanId = clanId;
    }

    public void assignTask(Task task) {
        this.task = task;
    }

    public TransactionInfo performTask() {
        TransactionInfo transactionInfo = new TransactionInfo(id, clanId, task.getId());
        transactionInfo.setStartTime(LocalDateTime.now());

        for(int i = 0; i < task.getAward()*DIFFICULTY_COEFFICIENT; i++) {
            doSomethingVeryHard(i);
        }

        transactionInfo.setCompleteTime(LocalDateTime.now());
        gold += task.getAward();
        transactionInfo.setGoldDelta(gold*CLAN_CONTRIBUTION_PERCENTAGE/100);
        return transactionInfo;
    }

    private void doSomethingVeryHard(int i) {
        int a = i;
        int b = i * 2;
        a = a + b;
        b = a - b;
        a = a - b;
    }
}
