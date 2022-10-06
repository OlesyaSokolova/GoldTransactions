package com.skytecgames.testtask.sokolova.service;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.model.Task;

//TODO: this class should work with info about completed tasks
public interface TaskService {
    Task getById(long taskId);

    boolean save(Task task);
}
