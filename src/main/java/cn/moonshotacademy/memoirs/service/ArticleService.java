package cn.moonshotacademy.memoirs.service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {
    ArticleDto getArticleById(int id);
}
