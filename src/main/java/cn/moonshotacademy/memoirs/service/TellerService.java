package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ResponseDto;
import cn.moonshotacademy.memoirs.dto.TellerDto;
import cn.moonshotacademy.memoirs.entity.TellerEntity;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public interface TellerService {
    ResponseDto<TellerDto> createTeller(TellerDto tellerDto);

    List<TellerEntity> getTellersByUserId(Integer userId);

    TellerEntity getTellerById(Integer tellerId);

    Resource LoadAvatarAsResource(String filename);

    List<ArticleDto> getArticlesByTellerId(Integer tellerId);

    List<TellerEntity> getList();

    TellerEntity findTeller(String name);
}
