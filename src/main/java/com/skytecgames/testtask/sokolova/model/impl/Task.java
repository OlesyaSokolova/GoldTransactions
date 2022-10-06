package com.skytecgames.testtask.sokolova.model.impl;


import com.skytecgames.testtask.sokolova.model.TaskType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor
@Getter
public class Task {

    public static final String PARAMETRIZED_STATEMEND = "INSERT INTO tasks (type, description, award) " +
                                                                   "values (?,    ?,           ?    )";

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

    /*//@Override
    public String getParametrizedStatement() {
        return "INSERT INTO tasks (type, description, award) " +
                "VALUES (" +
                "'" + type + "'" + ", " +
                "'" + description + "'" + ", " +
                + award + ")";
    }*/

    //@Override
    public static Task generateRandomInstance() {
        return generateRandomTask();
    }

}
