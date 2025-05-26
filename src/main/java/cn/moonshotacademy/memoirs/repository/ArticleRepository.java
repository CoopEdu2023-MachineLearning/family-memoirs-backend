package cn.moonshotacademy.memoirs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.memoirs.entity.ArticleEntity;

@Repository
public interface ArticleRepository extends JpaRepository<ArticleEntity, Integer> {
    @Query("SELECT a.id FROM ArticleEntity a WHERE a.status = :status")
    List<Integer> findIdsByStatus(@Param("status") String status);
}