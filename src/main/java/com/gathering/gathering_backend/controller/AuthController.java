package com.gathering.gathering_backend.controller;

import com.gathering.gathering_backend.dto.UserRegisterDTO;
import com.gathering.gathering_backend.dto.UserResponseDTO;
import com.gathering.gathering_backend.dto.UserUpdateDTO;
import com.gathering.gathering_backend.entity.User;
import com.gathering.gathering_backend.service.UserService;
import com.gathering.gathering_backend.util.JwtUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UserResponseDTO> updateUser(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody UserUpdateDTO userUpdateDTO) {
        // Authorization 헤더에서 토큰 추출
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body(null); // 인증 실패 시 401 반환
        }
        String token = authorizationHeader.substring(7); // "Bearer " 제거

        // JWT에서 사용자 ID 추출
        String userId;
        try {
            userId = JwtUtil.extractUserId(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(null); // JWT 유효하지 않을 경우 401 반환
        }

        // userId를 통해 userNo를 가져옴
        User user = userService.findByUserId(userId);

        if (user == null) {
            return ResponseEntity.status(404).body(null); // 사용자 정보가 없을 경우 404 반환
        }

        // 회원 정보 수정
        User updatedUser = userService.updateUser(user.getUserNo(), userUpdateDTO);

        // 수정된 사용자 정보를 UserResponseDTO로 변환
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setUserNo(updatedUser.getUserNo());
        responseDTO.setUserId(updatedUser.getUserId());
        responseDTO.setUserName(updatedUser.getUserName());
        responseDTO.setUserDescription(updatedUser.getUserDescription());
        responseDTO.setUserType(updatedUser.getUserType());

        // 관심사 리스트를 DTO로 변환
        List<UserResponseDTO.UserInterestDTO> userInterestDTOs = updatedUser.getUserInterests().stream()
                .map(userInterest -> {
                    UserResponseDTO.UserInterestDTO interestDTO = new UserResponseDTO.UserInterestDTO();
                    interestDTO.setUserInterestId(userInterest.getUserInterestId()); // UserInterest ID 설정
                    interestDTO.setInterestNo(userInterest.getInterest().getInterestNo()); // Interest ID 설정
                    interestDTO.setInterestName(userInterest.getInterest().getInterestName()); // Interest 이름 설정
                    return interestDTO;
                }).toList();

        responseDTO.setUserInterests(userInterestDTOs);

        return ResponseEntity.ok(responseDTO);
    }


}