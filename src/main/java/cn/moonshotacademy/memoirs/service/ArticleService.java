package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;

public interface ArticleService {
    ArticleDto getArticleById(int id);
}
