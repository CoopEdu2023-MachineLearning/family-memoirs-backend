package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.TellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TellerRepository extends JpaRepository<TellerEntity, Integer> {
}