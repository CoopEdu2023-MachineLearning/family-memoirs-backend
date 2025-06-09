package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.AudioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AudioRepository extends JpaRepository<AudioEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by articleId
    List<AudioEntity> findAllByArticleId(Long articleId);
}
