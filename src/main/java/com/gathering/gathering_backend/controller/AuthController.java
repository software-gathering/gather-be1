package com.gathering.gathering_backend.controller;

import com.gathering.gathering_backend.dto.UserRegisterDTO;
import com.gathering.gathering_backend.dto.UserUpdateDTO;
import com.gathering.gathering_backend.entity.User;
import com.gathering.gathering_backend.service.UserService;
import com.gathering.gathering_backend.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        userService.registerUser(userRegisterDTO);
        return ResponseEntity.ok("회원가입 성공!");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userId, @RequestParam String userPw) {
        User user = userService.login(userId, userPw); // 인증 수행
        String token = JwtUtil.generateToken(user.getUserId()); // JWT 생성
        return ResponseEntity.ok(token); // JWT 반환
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 초기화
        return ResponseEntity.ok("로그아웃 성공!");
    }

    // 회원정보 수정
    @PutMapping("/update")
    public ResponseEntity<User> updateUser(@RequestHeader("Authorization") String authorizationHeader,
                                           @RequestBody UserUpdateDTO userUpdateDTO) {
        // Authorization 헤더에서 토큰 추출
        String token = authorizationHeader.substring(7); // "Bearer " 제거
        String userId = JwtUtil.extractUserId(token); // JWT에서 사용자 ID 추출

        // userId를 통해 userNo를 가져옴
        User user = userService.findByUserId(userId);

        // 회원 정보 수정
        User updatedUser = userService.updateUser(user.getUserNo(), userUpdateDTO);
        return ResponseEntity.ok(updatedUser);
    }
}