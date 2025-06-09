package cn.moonshotacademy.memoirs.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.moonshotacademy.memoirs.dto.ArticleDto;
import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.exception.BusinessException;
import cn.moonshotacademy.memoirs.exception.ExceptionEnum;
import cn.moonshotacademy.memoirs.repository.ArticleRepository;
import cn.moonshotacademy.memoirs.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public int createArticle(ArticleDto articleDto) throws BusinessException {
        ArticleEntity articleEntity = new ArticleEntity();
        articleRepository.save(articleEntity);
        System.out.println(articleEntity.getId());
        return articleEntity.getId();
    }

    @Override
    public void uploadArticle(ArticleDto articleDto) throws BusinessException {
        ArticleEntity articleEntity = new ArticleEntity();
        BeanUtils.copyProperties(articleDto, articleEntity); // copyProperties(source, target)

        articleEntity.setStatus("unverified"); // unsubmitted unverified verified
        articleEntity.setDescriptionStatus("unverified");
        articleEntity.setTextStatus("unverified");
        articleRepository.save(articleEntity);
    }

    @Override
    public List<Integer> searchUnverifiedArticle(ArticleDto articleDto) throws BusinessException {
        List<Integer> unverifiedIds = articleRepository.findIdsByStatus("unverified");
        if (unverifiedIds.isEmpty())
            throw new BusinessException(ExceptionEnum.NO_UNVERIFIED_ARTICLE);
        return unverifiedIds;
    }

    @Override
    public List<Integer> searchVerifiedArticle(ArticleDto articleDto) throws BusinessException {
        List<Integer> verifiedIds = articleRepository.findIdsByStatus("verified");
        if (verifiedIds.isEmpty())
            throw new BusinessException(ExceptionEnum.NO_VERIFIED_ARTICLE);
        return verifiedIds;
    }

}
