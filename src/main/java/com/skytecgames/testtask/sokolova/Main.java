package com.skytecgames.testtask.sokolova;

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
