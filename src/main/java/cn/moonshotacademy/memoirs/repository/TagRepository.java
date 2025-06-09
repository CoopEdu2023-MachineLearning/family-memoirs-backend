package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.TagEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    List<TagEntity> findByRecommendTrue();
}
