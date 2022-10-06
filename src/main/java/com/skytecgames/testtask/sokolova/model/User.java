package com.skytecgames.testtask.sokolova.model;

public class User {
    private String name;
    private String email;
    private int clanId;
    private int gold;

    public User(String name, String email, int clanId) {
        this.name = name;
        this.email = email;
        this.clanId = clanId;
    }
}
