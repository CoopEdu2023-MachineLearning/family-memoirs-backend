package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import java.util.List;

public interface TellerService {
    ResponseDto<TellerDto> createTeller(TellerDto tellerDto);
    List<TellerEntity> getTellersByUserId(Integer userId);
}