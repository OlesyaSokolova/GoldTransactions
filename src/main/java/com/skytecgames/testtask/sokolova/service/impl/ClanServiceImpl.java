package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.repository.ClanRepository;
import com.skytecgames.testtask.sokolova.service.ClanService;

import java.util.ArrayList;

public class ClanServiceImpl implements ClanService {

    private final ClanRepository clanRepository;

    public ClanServiceImpl(ClanRepository clanRepository) {
        this.clanRepository = clanRepository;
    }

    @Override
    public Clan getById(long clanId) {
       //return clanRepository.getById(clanId);
        return null;
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
}
