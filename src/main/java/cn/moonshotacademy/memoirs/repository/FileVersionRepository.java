package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.FileVersionEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileVersionRepository extends JpaRepository<FileVersionEntity, Long> {
    Optional<FileVersionEntity> findByIsNewAndBaseName(boolean isNew, String basename);
}
