package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;

public interface TellerService {
    ResponseDto<TellerDto> createTeller(TellerDto tellerDto);
}