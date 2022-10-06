package com.skytecgames.testtask.sokolova.service.impl;

import com.skytecgames.testtask.sokolova.model.Clan;
import com.skytecgames.testtask.sokolova.repository.ClanRepository;
import com.skytecgames.testtask.sokolova.service.ClanService;

public class ClanServiceImpl implements ClanService {

    private ClanRepository clanRepository;

    @Override
    public Clan get(long clanId) {
        return null;
    }

    @Override
    public boolean save(Clan clan) {
        return false;
    }


}
