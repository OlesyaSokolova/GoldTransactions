package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {

        try {
            GameController gameController = GameController.getInstance();
            //todo: ъединить два метода, а то сейчас init омжет быть забыт
            gameController.init();
            gameController.start();
            //tODO: results will be shown in the logs
            //gameController.printResult();
            //dbMManager.initDB();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
