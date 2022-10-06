package com.skytecgames.testtask.sokolova.repository.impl;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.model.Task;
import com.skytecgames.testtask.sokolova.repository.RepositoryInterface;

import java.util.ArrayList;

//TODO: no need to create dao package
public class TaskRepository implements RepositoryInterface<Task> {

    private final ConnectionPoolWrapper connectionPoolWrapper;

    public TaskRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
    }

    @Override
    public ArrayList<Task> getAll() {
        return null;
    }

    @Override
    public Task getById(long id) {
        return null;
    }

   /* private void executeBatch() throws SQLException {
        if(statement != null) {
            statement.executeBatch();
        }
    }*/

    /*public void addTaskInsertionToBatch(Statement statement, Task task) throws SQLException {
        statement.addBatch(task.getInsertStatement());
    }*/
}
