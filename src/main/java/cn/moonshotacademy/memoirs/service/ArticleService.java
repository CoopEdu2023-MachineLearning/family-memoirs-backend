package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {
    public void uploadArticle(ArticleDto articleDto);
    ArticleDto getArticleById(int id);
}
