package cn.moonshotacademy.memoirs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.service.TellerService;

@Service
public class TellerServiceImpl implements TellerService {

    @Autowired
    private TellerRepository tellerRepository;

    @Override
    public TellerEntity[] getList() {
        TellerEntity[] tellers = tellerRepository.findAll().toArray(new TellerEntity[0]);
        if (tellers == null || tellers.length == 0) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        return tellers;
    }

    @Override
    public TellerEntity findTeller(String name) {
        TellerEntity teller = tellerRepository.findByNameOld(name)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));
        if (teller == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND);
        }
        return teller;
    }
}
