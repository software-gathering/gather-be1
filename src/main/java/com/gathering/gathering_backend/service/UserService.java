package com.gathering.gathering_backend.service;

import com.gathering.gathering_backend.dto.UserRegisterDTO;
import com.gathering.gathering_backend.dto.UserUpdateDTO;
import com.gathering.gathering_backend.entity.User;

public interface  UserService {
    void registerUser(UserRegisterDTO userRegisterDTO); // 회원가입

    User login(String userId, String userPw); // 로그인

    User updateUser(Long userNo, UserUpdateDTO userUpdateDTO); // 유저 업데이트

    User findByUserId(String userId);
}
