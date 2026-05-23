package com.vsign.backend.auth.service;

import com.vsign.backend.auth.dto.ProfileResponse;
import com.vsign.backend.auth.dto.UpdateProfileRequest;
import com.vsign.backend.auth.persistence.UserEntity;
import com.vsign.backend.auth.persistence.UserRepository;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {

    private final AuthService authService;
    private final UserRepository userRepository;

    public ProfileService(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    public ProfileResponse getProfile(String email) {
        AuthService.UserProfile profile = authService.findProfileByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        return ProfileResponse.fromUser(
                profile.email(),
                profile.fullName(),
                profile.role(),
                profile.avatarUrl(),
                profile.accountType(),
                profile.totalXp(),
                profile.currentStreak(),
                profile.longestStreak()
        );
    }

    @Transactional
    public ProfileResponse updateProfile(String email, UpdateProfileRequest request) {
        UserEntity user = userRepository.findByEmail(email.toLowerCase())
                .filter(UserEntity::isActive)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        user.updateProfile(request.fullName(), request.avatarUrl());

        return ProfileResponse.fromUser(
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getAvatarUrl(),
                user.getAccountType(),
                user.getTotalXp(),
                user.getCurrentStreak(),
                user.getLongestStreak()
        );
    }
}
