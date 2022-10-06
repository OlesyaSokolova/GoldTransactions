package com.skytecgames.testtask.sokolova.model.impl;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
public class User {

    private static final int NAME_LENGTH = 5;
    private static final String EMAIL_POSTFIX = "@mail.com";
    private static final int INITIAL_GOLD = 30;

    public static final String PARAMETRIZED_STATEMENT = "INSERT INTO users (name, email, clanid, gold) " +
                                                                   "values (?,    ?,     ?,      ?   )";

    private final String name;
    private final String email;
    private int clanId;
    private int gold = INITIAL_GOLD;
    private Task task;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
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

    public void updateGold(int delta) {
        gold += delta;
    }

    public void assignToClan(int clanId) {
        this.clanId = clanId;
    }

    public void assignTask(Task task) {
        this.task = task;
    }
}
