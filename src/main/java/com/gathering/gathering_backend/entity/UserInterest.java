package com.gathering.gathering_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userInterestId;

    @ManyToOne
    @JoinColumn(name = "userNo", nullable = false)
    private User user; // 사용자와의 연결

    @ManyToOne
    @JoinColumn(name = "interestNo", nullable = false)
    private Interest interest; // 관심사와의 연결
}
