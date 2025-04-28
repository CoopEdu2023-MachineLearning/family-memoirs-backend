package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    // Custom query methods can be defined here if needed
    // For example, find by author or title
    // List<ArticleEntity> findByAuthor(String author);
}
