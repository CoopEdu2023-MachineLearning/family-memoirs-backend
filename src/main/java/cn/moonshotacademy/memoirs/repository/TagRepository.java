package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
}
