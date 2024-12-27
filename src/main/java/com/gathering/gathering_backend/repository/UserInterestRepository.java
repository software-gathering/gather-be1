package com.gathering.gathering_backend.repository;

import com.gathering.gathering_backend.entity.UserInterest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestRepository extends JpaRepository<UserInterest, Long> {
    void deleteByUserUserNo(Long userNo); // 특정 사용자의 관심사 삭제
}
