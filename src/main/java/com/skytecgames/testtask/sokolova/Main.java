package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.db.DataBaseInitializer;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {

        try {
            GameController gameController = GameController.getInstance();
            gameController.init();
            gameController.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
