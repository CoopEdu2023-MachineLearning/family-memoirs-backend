package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.dto.ArticleListDto;

import org.springframework.stereotype.Service;
import cn.moonshotacademy.memoirs.dto.WaterDto;
import org.springframework.data.domain.Page;
import java.util.List;

@Service
public interface ArticleService {
    void uploadArticle(ArticleDto articleDto);
    ArticleDto getArticleById(int id);
    List<ArticleListDto> getAllArticles();
    Page<WaterDto> getArticles(int page, int size);
}
