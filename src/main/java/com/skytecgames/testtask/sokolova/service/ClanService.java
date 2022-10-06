package com.skytecgames.testtask.sokolova.service;

import com.skytecgames.testtask.sokolova.model.Clan;

// Есть сервис, посвященный кланам.
// Да это выглядит как 'репозиторий'.
// Но это сервис, просто все остппльные методы не нужны для примера
public interface ClanService {
    Clan get(long clanId);

    boolean save(Clan clan);
}
