package com.gathering.gathering_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateDTO {
    private String userPw; // 비밀번호 (Optional)
    private String userName; // 닉네임 (Optional)
    private String userDescription; // 자기소개 (Optional)
}