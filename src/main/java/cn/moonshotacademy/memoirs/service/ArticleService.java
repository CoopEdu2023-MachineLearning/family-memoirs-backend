package cn.moonshotacademy.memoirs.service;

import java.util.List;

import cn.moonshotacademy.memoirs.dto.ArticleDto;

public interface ArticleService {

    public int createArticle(ArticleDto articleDto);

    public void uploadArticle(ArticleDto articleDto);

    public List<Integer> searchUnverifiedArticle(ArticleDto articleDto);

    public List<Integer> searchVerifiedArticle(ArticleDto articleDto);
}
