package cn.moonshotacademy.memoirs.service.impl;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.UserDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.UserEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.repository.UserRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    public ArticleDto getArticleById(int id) {
        ArticleEntity article = articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.ARTICLE_NOT_FOUND));;

        UserEntity userEntity = userRepository.findById(article.getUserId())
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));

        UserDto userDto = UserDto.builder()
                .id(userEntity.getId())
                .username(userEntity.getUsername())
                .build();

        return ArticleDto.builder()
                .id(article.getId())
                .era(article.getEra())
                .tellerId(article.getTellerId())
                .startYear(article.getStartYear())
                .endYear(article.getEndYear())
                .startMonth(article.getStartMonth())
                .endMonth(article.getEndMonth())
                .location(article.getLocation())
                .text(article.getText())
                .user(userDto)
                .description(article.getDescription())
                .textStatus(article.getTextStatus())
                .descriptionStatus(article.getDescriptionStatus())
                .build();
    }
}