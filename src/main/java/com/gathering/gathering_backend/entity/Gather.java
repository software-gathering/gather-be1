package com.gathering.gathering_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Gather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gatherNo;

    @Column(nullable = false, length = 20)
    private String gatherRole;

    @Column(nullable = false)
    private int currentParticipants;

    @Column(nullable = false)
    private int maxParticipants;

    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userNo", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postNo", nullable = false)
    private Post post;

}
