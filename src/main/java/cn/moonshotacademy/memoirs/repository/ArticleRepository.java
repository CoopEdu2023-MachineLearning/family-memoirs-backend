package cn.moonshotacademy.memoirs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import cn.moonshotacademy.memoirs.entity.TagEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    @Query("SELECT t FROM ArticleEntity a JOIN a.tagList t WHERE a.id = :articleId")
    List<TagEntity> findTagsByArticleId(@Param("articleId") Integer articleId);

    List<ArticleEntity> findByTellerId(Integer tellerId);

    @Query("SELECT a.id FROM ArticleEntity a WHERE a.status = :status")
    List<Integer> findIdsByStatus(@Param("status") String status);
}
