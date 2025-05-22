package cn.moonshotacademy.memoirs.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public void uploadArticle(ArticleDto articleDto) {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(articleDto, articleEntity); // copyProperties(source, target)
        articleRepository.save(articleEntity);
    }

}
