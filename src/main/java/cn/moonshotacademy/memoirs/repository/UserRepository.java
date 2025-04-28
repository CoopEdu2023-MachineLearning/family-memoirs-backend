package cn.moonshotacademy.memoirs.repository;

import cn.moonshotacademy.memoirs.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    boolean existsByUsername(String username);
}
