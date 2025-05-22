package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ArticleListDto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    void uploadArticle(ArticleDto articleDto);
    ArticleDto getArticleById(int id);
    List<ArticleListDto> getAllArticles();
}
