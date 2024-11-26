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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postNo;

    @Column(nullable = false, length = 30)
    private String postName;

    @Column(nullable = false)
    private int postMember;

    @Column(nullable = false)
    private int postMaxMem;

    @Column(columnDefinition = "TEXT")
    private String postDetail;

    @Column(columnDefinition = "TEXT")
    private String postGather;

    private String postImg;

    @Column(nullable = false)
    private LocalDateTime postDate;

    @Column(nullable = false)
    private boolean postComplete;

    private LocalDateTime postEndDate;

    private LocalDateTime postDeleteDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cxNo", nullable = true)
    private ExternalAct externalAct;

}
