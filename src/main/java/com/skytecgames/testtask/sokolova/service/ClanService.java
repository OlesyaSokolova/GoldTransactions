package com.skytecgames.testtask.sokolova.service;

import com.skytecgames.testtask.sokolova.model.Clan;

import java.util.ArrayList;

// Есть сервис, посвященный кланам.
// Да это выглядит как 'репозиторий'.
// Но это сервис, просто все остппльные методы не нужны для примера
public interface ClanService {
    Clan getById(long clanId);

    boolean save(Clan clan);

    ArrayList<Clan> getAll();
}
