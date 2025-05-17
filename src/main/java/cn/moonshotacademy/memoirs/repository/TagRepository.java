package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Integer> {
    List<TagEntity> findAllById(int id);
}
