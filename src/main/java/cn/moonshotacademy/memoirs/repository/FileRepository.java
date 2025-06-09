package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.FileEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {

}
