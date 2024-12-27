package com.gathering.gathering_backend.repository;

import com.gathering.gathering_backend.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
    boolean existsByInterestName(String interestName); // 관심사가 존재하는지 확인
}
