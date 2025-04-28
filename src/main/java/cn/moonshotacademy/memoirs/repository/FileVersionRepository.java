package cn.moonshotacademy.memoirs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.moonshotacademy.memoirs.entity.FileVersionEntity;

public interface FileVersionRepository extends JpaRepository<FileVersionEntity, Long> {
    Optional<FileVersionEntity> findByIsNewAndBaseName(boolean isNew, String basename);
}   
