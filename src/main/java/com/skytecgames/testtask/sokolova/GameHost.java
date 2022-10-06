package com.skytecgames.testtask.sokolova;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.service.ClanService;
import com.skytecgames.testtask.sokolova.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class GameHost {
    
    private final ClanService clanService;
    private final TaskService taskService;

    private List<Clan> clans;
    
    public GameHost(ClanService clanService, TaskService taskService) {
        this.clanService = clanService;
        this.taskService = taskService;
    }

    public void prepareGameConditions() {
        clans = new ArrayList<>(clanService.getAll());
    }

    public void start() {
    }


}
