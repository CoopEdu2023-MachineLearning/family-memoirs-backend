package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by articleId
    List<ImageEntity> findAllByArticleId(Long articleId);
}
