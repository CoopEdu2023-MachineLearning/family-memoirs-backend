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
    public ArticleEntity getArticleById(int id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ExceptionEnum.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto getArticleDtoById(int id) {
        ArticleEntity article = getArticleById(id);

        // 获取文章关联的用户对象
        UserEntity userEntity = userRepository.findById(article.getUser())
                .orElseThrow(() -> new BusinessException(ExceptionEnum.USER_NOT_FOUND));

        // 转换用户实体为DTO
        UserDto userDto = UserDto.builder()
                .id(Long.valueOf(userEntity.getId()))
                .username(userEntity.getUsername())
                .build();

        // 构建并返回文章DTO，包含完整的用户信息
        return ArticleDto.builder()
                .id(article.getId())
                .era(article.getEra())
                .startDate(article.getStartDate())
                .endDate(article.getEndDate())
                .location(article.getLocation())
                .text(article.getText())
                .user(userDto)
                .description(article.getDescription())
                .status(article.getStatus())
                .build();
    }
}