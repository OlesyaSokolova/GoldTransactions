package com.skytecgames.testtask.sokolova.model;

import lombok.Getter;
import java.util.concurrent.ThreadLocalRandom;

@Getter
public class Task {

    public static final String PARAMETRIZED_STATEMEND = "INSERT INTO tasks (type, description, award) " +
                                                                   "values (?,    ?,           ?    )";

    private static final int MAX_AWARD = 1051;
    private static final int MIN_AWARD = 15;

    private int id;
    private final TaskType type;
    private final String description;
    private final int award;

    private static final TaskType[] possibleTypes = TaskType.values();

    public Task(TaskType type, String description, int award) {
        this.type = type;
        this.description = description;
        this.award = award;
    }

    public Task(int id, TaskType type, String description, int award) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.award = award;
    }

    public static Task generateRandomTask() {
        return new Task(
                possibleTypes[ThreadLocalRandom.current().nextInt(0, possibleTypes.length)],
                "Blah-blah-blah! If you complete the task, you will receive some gold! Blah-blah-blah...",
                ThreadLocalRandom.current().nextInt(MIN_AWARD, MAX_AWARD)
        );
    }

    public static Task generateRandomInstance() {
        return generateRandomTask();
    }
}
