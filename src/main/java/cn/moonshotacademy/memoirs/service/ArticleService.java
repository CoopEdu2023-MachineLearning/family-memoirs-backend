package cn.moonshotacademy.memoirs.service;

import java.util.List;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ArticleListDto;
import cn.moonshotacademy.memoirs.dto.WaterDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {
    void uploadArticle(ArticleDto articleDto);

    ArticleDto getArticleById(int id);

    List<ArticleListDto> getAllArticles();
    List<ArticleEntity> getAllArticleEntities();

    Page<WaterDto> getArticles(int page, int size);

    int createArticle(ArticleDto articleDto);

    List<Integer> searchUnverifiedArticle(ArticleDto articleDto);

    List<Integer> searchVerifiedArticle(ArticleDto articleDto);
}
