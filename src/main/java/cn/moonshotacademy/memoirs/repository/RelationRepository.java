package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.RelationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RelationRepository extends JpaRepository<RelationEntity, Integer> {
    Optional<RelationEntity> findByName(String name);
}