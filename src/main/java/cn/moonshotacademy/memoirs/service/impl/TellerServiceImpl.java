package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TellerServiceImpl implements TellerService {

    @Autowired
    private TellerRepository tellerRepository;

    @Override
    public ResponseDto<TellerDto> createTeller(TellerDto tellerDto) {
        try {
            TellerEntity teller = new TellerEntity();
            teller.setIdentity(tellerDto.getIdentity());
            teller.setNameNew(tellerDto.getNameNew());
            teller.setGender(tellerDto.getGender()); 
            teller.setBirthplace(tellerDto.getBirthplace());
            teller.setBirthdate(tellerDto.getBirthdate());
            teller.setIntroNew(tellerDto.getIntroNew());
            teller.setAvatarNew(tellerDto.getAvatarNew());
            teller.setNameState("pending");
            teller.setIntroState("pending");
            teller.setAvatarState("pending");
            TellerEntity savedTeller = tellerRepository.save(teller);
            return ResponseDto.success(tellerDto);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.TELLER_CREATE_FAILED);
        }
    }
}