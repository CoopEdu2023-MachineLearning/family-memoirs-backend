package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.entity.TellerEntity;

public interface TellerService {

    public TellerEntity[] getList();

    public TellerEntity findTeller(String name);
}
