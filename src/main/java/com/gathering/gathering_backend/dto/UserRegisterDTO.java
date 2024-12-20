package com.gathering.gathering_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterDTO {
    private String userId;
    private String userPw;
    private String userName;
}
