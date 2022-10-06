package com.skytecgames.testtask.sokolova.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

// Упрощенный объект клана
@AllArgsConstructor
@Getter
public class Clan {
    private long id;     // id клана
    private String name; // имя клана
    private int gold;    // текущее количество золота в казне клана
}
