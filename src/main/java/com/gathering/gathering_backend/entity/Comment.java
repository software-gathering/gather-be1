package com.gathering.gathering_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comtNo;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String comtDetail;

    private int comtClass;

    private int comtGroup;

    @Column(nullable = false)
    private LocalDateTime comtDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postNo", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private User user;
}
