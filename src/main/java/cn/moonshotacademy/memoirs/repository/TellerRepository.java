package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.TellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TellerRepository extends JpaRepository<TellerEntity, Integer> {
    TellerEntity findById(int id);

    List<TellerEntity> findAllByUserId(String userId);
}
