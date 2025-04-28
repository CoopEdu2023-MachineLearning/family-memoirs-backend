package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;

public interface ArticleService {
    ArticleEntity getArticleById(int id);

    ArticleDto getArticleDtoById(int id);
}
