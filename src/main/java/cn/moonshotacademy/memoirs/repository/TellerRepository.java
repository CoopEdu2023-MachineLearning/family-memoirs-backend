package cn.moonshotacademy.memoirs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.moonshotacademy.memoirs.entity.TellerEntity;

@Repository
public interface TellerRepository extends JpaRepository<TellerEntity, Integer> {

    Optional<TellerEntity> findByNameOld(String nameOld);

}