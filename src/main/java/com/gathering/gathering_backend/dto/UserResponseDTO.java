package com.gathering.gathering_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private Long userNo;
    private String userId;
    private String userName;
    private String userDescription;
    private Integer userType;
    private List<UserInterestDTO> userInterests;

    @Getter
    @Setter
    public static class UserInterestDTO {
        private Long userInterestId; // UserInterest의 ID
        private Long interestNo;     // Interest의 ID
        private String interestName; // Interest의 이름
    }
}
