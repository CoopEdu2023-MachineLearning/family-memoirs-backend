package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemoRepository extends JpaRepository<DemoEntity, Integer> {
    DemoEntity findByField(String field);
}
