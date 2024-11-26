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
public class ExternalAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long exNo;

    @Column(nullable = false, length = 50)
    private String exName;

    private String exLink;

    private String exHost;

    private LocalDateTime exStart;

    private LocalDateTime exEnd;

    private LocalDateTime exDelete;

    private int exFlag;
}
