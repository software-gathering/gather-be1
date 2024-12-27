package com.gathering.gathering_backend.service;

import com.gathering.gathering_backend.dto.UserRegisterDTO;
import com.gathering.gathering_backend.dto.UserUpdateDTO;
import com.gathering.gathering_backend.entity.Interest;
import com.gathering.gathering_backend.entity.User;
import com.gathering.gathering_backend.entity.UserInterest;
import com.gathering.gathering_backend.repository.InterestRepository;
import com.gathering.gathering_backend.repository.UserInterestRepository;
import com.gathering.gathering_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserInterestRepository userInterestRepository;
    private final InterestRepository interestRepository;

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        // 회원가입 시 필요한 필드만 처리
        User user = new User();
        user.setUserId(userRegisterDTO.getUserId());
        user.setUserPw(passwordEncoder.encode(userRegisterDTO.getUserPw())); // 비밀번호 암호화
        user.setUserName(userRegisterDTO.getUserName());
        user.setUserType(0); // 기본값 0 설정

        userRepository.save(user); // 저장
    }

    @Override
    public User login(String userId, String userPw) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 ID가 존재하지 않습니다."));

        if (!passwordEncoder.matches(userPw, user.getUserPw())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return user; // 로그인 성공
    }

    @Override
    public User updateUser(Long userNo, UserUpdateDTO userUpdateDTO) {
        // 사용자 정보 가져오기
        User user = userRepository.findById(userNo)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 변경
        if (userUpdateDTO.getUserPw() != null) {
            user.setUserPw(passwordEncoder.encode(userUpdateDTO.getUserPw()));
        }

        // 이름 변경
        if (userUpdateDTO.getUserName() != null) {
            user.setUserName(userUpdateDTO.getUserName());
        }

        // 설명 변경
        if (userUpdateDTO.getUserDescription() != null) {
            user.setUserDescription(userUpdateDTO.getUserDescription());
        }

        // 관심사 업데이트
        if (userUpdateDTO.getInterests() != null && !userUpdateDTO.getInterests().isEmpty()) {
            userInterestRepository.deleteByUserUserNo(userNo); // 기존 관심사 삭제

            for (Long interestId : userUpdateDTO.getInterests()) {
                Interest interest = interestRepository.findById(interestId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid interest ID: " + interestId));

                UserInterest userInterest = new UserInterest();
                userInterest.setUser(user);
                userInterest.setInterest(interest);
                userInterestRepository.save(userInterest);
            }
        }

        return userRepository.save(user);
    }

    @Override
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

}
