package cn.moonshotacademy.memoirs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    @Query("SELECT t FROM ArticleEntity a JOIN a.tagList t WHERE a.id = :articleId")
    List<TagEntity> findTagsByArticleId(@Param("articleId") Integer articleId);
}