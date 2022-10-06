package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;

public class ClanRepository {
    private final DataBaseInitializer dataBaseManager;

    public ClanRepository(DataBaseInitializer dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }
}
