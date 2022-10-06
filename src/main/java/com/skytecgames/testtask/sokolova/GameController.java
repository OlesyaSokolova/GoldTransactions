package com.skytecgames.testtask.sokolova;


/*Public static Controller makeController() {
        DataSource dataSource = new DataSource(ConnectionPool.getInstance)
        ConferenceDao conferenceDao = new ConferenceDao(dataSource)
        SpeakerDao speakerDao = new SpeakerDao(dataSource)
        TalkDao talkDao = new TalkDao(dataSource);
        Return new Controller(conferenceDao, speakerDao, talkDao);
        }

        Дата сурс получает коннекшн пул (любым способом)
        Этот метод не делает ничего, но создает всю стойку с юнитами, втыкая в неё провода
        Уже внутри есть объекты-компоненты, он знает какие кому нужны. Внутри они инстанцируются и подпихиваются друг другу
        Это целая стойка, а на верхушке контроллер - в итоге получаем контроллер
        Нет недостатков синглтона:
        Можем тестировать*/

import com.skytecgames.testtask.sokolova.db.ConnectionPoolWrapper;
import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;
import com.skytecgames.testtask.sokolova.repository.ClanRepository;
import com.skytecgames.testtask.sokolova.repository.TaskRepository;
import com.skytecgames.testtask.sokolova.service.ClanService;
import com.skytecgames.testtask.sokolova.service.TaskService;
import com.skytecgames.testtask.sokolova.service.impl.ClanServiceImpl;
import com.skytecgames.testtask.sokolova.service.impl.TaskServiceImpl;

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
       dataBaseInitializer.initDB();
    }

    public void start() {

        ClanRepository clanRepository = new ClanRepository(connectionPool);
        TaskRepository taskRepository = new TaskRepository(connectionPool);

        ClanService clanService = new ClanServiceImpl(clanRepository);
        TaskService taskService = new TaskServiceImpl(taskRepository);

        GameHost gameHost = new GameHost(clanService, taskService);

        //todo: userpraperer - будет генерировать игроков
        gameHost.prepareUsers();
        //а вот здесь они уже запускаются в игру
        gameHost.start();
    }
}
