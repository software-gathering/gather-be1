package com.gathering.gathering_backend.repository;

import com.gathering.gathering_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId); // 사용자 ID로 조회
}
