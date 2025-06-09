package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.FileEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByName(String filename);

    List<FileEntity> findAllByIsDeletedTrue();
}
