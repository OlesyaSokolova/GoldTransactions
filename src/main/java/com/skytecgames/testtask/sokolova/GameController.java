package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;
import com.skytecgames.testtask.sokolova.model.impl.Clan;
import com.skytecgames.testtask.sokolova.model.impl.Task;
import com.skytecgames.testtask.sokolova.model.impl.User;
import com.skytecgames.testtask.sokolova.repository.impl.ClanRepository;
import com.skytecgames.testtask.sokolova.repository.impl.TaskRepository;
import com.skytecgames.testtask.sokolova.repository.impl.UserRepository;
import com.skytecgames.testtask.sokolova.service.Service;
import com.skytecgames.testtask.sokolova.service.impl.ClanService;
import com.skytecgames.testtask.sokolova.service.impl.TaskService;
import com.skytecgames.testtask.sokolova.service.impl.UserService;

import java.io.IOException;
import java.sql.SQLException;


public class GameController {
    private ConnectionPoolWrapper connectionPool;

    private static class GameControllerHolder {
        private static final GameController instance = new GameController();
    }

    public static GameController getInstance() {
        return GameControllerHolder.instance;
    }

    GameController() {
    }

    public void init() throws IOException, SQLException {

       connectionPool = new ConnectionPoolWrapper();
       DataBaseInitializer dataBaseInitializer = new DataBaseInitializer(connectionPool);
      // dataBaseInitializer.initDB();
    }

    public void start() {

        ClanRepository clanRepository = new ClanRepository(connectionPool);
        TaskRepository taskRepository = new TaskRepository(connectionPool);
        UserRepository userRepository = new UserRepository(connectionPool);

        Service<Clan> clanService = new ClanService(clanRepository);
        Service<Task> taskService = new TaskService(taskRepository);
        Service<User> userService = new UserService(userRepository);

        GameHost gameHost = new GameHost(clanService, taskService, userService);

        gameHost.prepareGameConditions();
        //а вот здесь они уже запускаются в игру
        gameHost.start();
    }
}
