package com.skytecgames.testtask.sokolova.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Getter
public class Task {

   /* private final String PARAMETRIZED_STATEMENT =  "insert into tasks (id, type, description, award) " +
            "values (?, ?, ?, ?)";*/

    private static final int MAX_AWARD = 1051;
    private static final int MIN_AWARD = 15;

    private final TaskType type;
    private final String description;
    private final int award;

    private static final TaskType[] possibleTypes = TaskType.values();

    public static Task generateRandomTask() {
        return new Task(
                possibleTypes[ThreadLocalRandom.current().nextInt(0, possibleTypes.length)],
                "Blah-blah-blah! If you complete the task, you will receive some gold! Blah-blah-blah...",
                ThreadLocalRandom.current().nextInt(MIN_AWARD, MAX_AWARD)
        );
    }

    public String getInsertStatement() {
       String test = "INSERT INTO tasks (type, description, award) " +
               "VALUES (" +
               "'" + type + "'" + ", " +
               "'" + description + "'" + ", " +
               + award + ")";
        return test;
    }
}
