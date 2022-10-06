package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Упрощенный объект клана
@AllArgsConstructor
@Getter
public class Clan {
    private final int id;     // id клана
    private final String name; // имя клана
    private int gold;    // текущее количество золота в казне клана

    public void updateGold(int delta) {
        gold += delta;
    }
}
