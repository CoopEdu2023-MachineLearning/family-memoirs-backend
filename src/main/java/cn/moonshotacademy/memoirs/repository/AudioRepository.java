package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.AudioEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AudioRepository extends JpaRepository<AudioEntity, Long> {
    // Custom query methods can be defined here if needed
    // For example, find by articleId
    List<AudioEntity> findAllByArticleId(Long articleId);
}
