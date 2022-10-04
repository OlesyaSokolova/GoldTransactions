package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.service.db.DataBaseManager;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    public static void main(String[] args) {
        log.info("newbweh");
        System.out.println("Hello world!");

        try {
            DataBaseManager dbMManager  = new DataBaseManager();
            dbMManager.initDB();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
