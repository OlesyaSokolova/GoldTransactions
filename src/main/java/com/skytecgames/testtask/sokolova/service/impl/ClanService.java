package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.repository.impl.ClanRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.util.ArrayList;

public class ClanService implements Service<Clan> {

    private final ClanRepository clanRepository;

    public ClanService(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    @Override
    public boolean save(Clan clan) {
        //clanRepository.save(clan);
        return false;
    }

    @Override
    public ArrayList<Clan> getAll() {
        return null;
    }

    @Override
    public Clan getById(long id) {
        return null;
    }
}
