package cn.moonshotacademy.memoirs.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cn.moonshotacademy.memoirs.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    boolean existsByUsername(String username);
}