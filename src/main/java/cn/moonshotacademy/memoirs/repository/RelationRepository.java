package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.RelationEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationRepository extends JpaRepository<RelationEntity, Integer> {
    Optional<RelationEntity> findByName(String name);
}
