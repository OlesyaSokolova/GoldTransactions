package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.repository.impl.ClanRepository;
import com.skytecgames.testtask.sokolova.service.Service;

import java.sql.SQLException;
import java.util.List;

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
    public Clan getRandom() throws SQLException {
        return clanRepository.getRandom();
    }

    @Override
    public List<Clan> getAll() throws SQLException {
        return clanRepository.getAll();
    }

    @Override
    public Clan getById(int id) throws SQLException {
       return clanRepository.getById(id);
    }
}
