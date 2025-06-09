package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TellerService {
    ResponseDto<TellerDto> createTeller(TellerDto tellerDto);

    TellerEntity getTellerById(Integer tellerId);

    Resource LoadAvatarAsResource(String filename);

    List<ArticleDto> getArticlesByTellerId(Integer tellerId);
}