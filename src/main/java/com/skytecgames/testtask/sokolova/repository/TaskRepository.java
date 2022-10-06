package com.skytecgames.testtask.sokolova.repository;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;

//TODO: no need to create dao package
public class TaskRepository {

    private final ConnectionPoolWrapper connectionPoolWrapper;

    public TaskRepository(ConnectionPoolWrapper connectionPoolWrapper) {
        this.connectionPoolWrapper = connectionPoolWrapper;
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
