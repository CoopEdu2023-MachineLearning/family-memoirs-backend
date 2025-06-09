package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.RelationEntity;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.RelationRepository;
import cn.moonshotacademy.memoirs.repository.TellerRepository;
import cn.moonshotacademy.memoirs.service.TellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TellerServiceImpl implements TellerService {

    @Autowired
    private TellerRepository tellerRepository;

    @Autowired
    private RelationRepository relationRepository;

    @Override
    public ResponseDto<TellerDto> createTeller(TellerDto tellerDto) {
        try {
            Integer userId = tellerDto.getUserId();
            if (userId == null) {
                throw new BusinessException(ExceptionEnum.INVALID_USER_ID);
            }

            List<TellerEntity> existingTellers = tellerRepository.findByUserId(userId);
            boolean hasSelf = existingTellers.stream().anyMatch(t -> "我自己".equals(t.getIdentity()));

            String identity;
            Integer relationId = null;
            String relationName = tellerDto.getRelationName();

            if (existingTellers.isEmpty()) {
                identity = "我自己";
            } else if (hasSelf) {
                if (relationName == null || relationName.trim().isEmpty()) {
                    throw new BusinessException(ExceptionEnum.INVALID_RELATION);
                }
                // 查询或创建 relation 记录
                Optional<RelationEntity> relationOpt = relationRepository.findByName(relationName);
                RelationEntity relation;
                if (relationOpt.isPresent()) {
                    relation = relationOpt.get();
                } else {
                    relation = new RelationEntity();
                    relation.setName(relationName);
                    relation = relationRepository.save(relation);
                }
                relationId = relation.getId();
                identity = "我自己的" + relationName;
            } else {
                throw new BusinessException(ExceptionEnum.MUST_CREATE_SELF_FIRST);
            }

            TellerEntity teller = new TellerEntity();
            teller.setIdentity(tellerDto.getIdentity());
            teller.setName_new(tellerDto.getNameNew());
            teller.setGender(tellerDto.getGender()); 
            teller.setUserId(userId);
            teller.setIdentity(identity);
            if (relationId != null) {
                RelationEntity relation = relationRepository.findById(relationId)
                    .orElseThrow(() -> new BusinessException(ExceptionEnum.INVALID_RELATION));
                teller.setRelation(relation);
            }
            teller.setGender(tellerDto.getGender());
            teller.setBirthplace(tellerDto.getBirthplace());
            teller.setBirthdate(tellerDto.getBirthdate());
            teller.setIntro_new(tellerDto.getIntroNew());
            teller.setAvatarNew(tellerDto.getAvatarNew());
            teller.setName_state("pending");
            teller.setIntro_state("pending");
            teller.setAvatar_state("pending");
            TellerEntity savedTeller = tellerRepository.save(teller);
            return ResponseDto.success(tellerDto);
        } catch (Exception e) {
            throw new BusinessException(ExceptionEnum.TELLER_CREATE_FAILED);
        }
    }

    @Override
    public List<TellerEntity> getTellersByUserId(Integer userId) {
        if (userId == null) {
            throw new BusinessException(ExceptionEnum.INVALID_USER_ID);
        }
        return tellerRepository.findByUserId(userId);
    }
}