package com.vsign.backend.auth.service;

import com.vsign.backend.auth.dto.AuthResponse;
import com.vsign.backend.auth.dto.LoginRequest;
import com.vsign.backend.auth.dto.RegisterRequest;
import com.vsign.backend.auth.persistence.UserEntity;
import com.vsign.backend.auth.persistence.UserRepository;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.common.security.JwtService;
import java.util.Locale;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        String email = normalizeAndValidateEmail(request.email());
        String password = validateRequiredText(request.password(), "password");
        String fullName = buildFullName(request.fullName(), email);

        if (userRepository.existsByEmail(email)) {
            throw new BusinessException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        UserEntity created = userRepository.save(new UserEntity(email, passwordEncoder.encode(password), fullName));
        String token = jwtService.generateToken(email, created.getRole());
        return authResponse(token, created);
    }

    public AuthResponse login(LoginRequest request) {
        String email = normalizeAndValidateEmail(request.email());
        String password = validateRequiredText(request.password(), "password");

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BusinessException(ErrorCode.INVALID_CREDENTIALS));

        if (!user.isActive()) {
            throw new BusinessException(ErrorCode.ACCOUNT_DISABLED);
        }
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new BusinessException(ErrorCode.INVALID_CREDENTIALS);
        }

        String token = jwtService.generateToken(email, user.getRole());
        return authResponse(token, user);
    }

    public Optional<UserProfile> findProfileByEmail(String email) {
        if (email == null) {
            return Optional.empty();
        }
        return userRepository.findByEmail(email.toLowerCase(Locale.ROOT))
                .filter(UserEntity::isActive)
                .map(user -> new UserProfile(
                        user.getEmail(),
                        user.getFullName(),
                        user.getRole(),
                        user.getAvatarUrl(),
                        user.getAccountType(),
                        user.getTotalXp(),
                        user.getCurrentStreak(),
                        user.getLongestStreak()
                ));
    }

    private static AuthResponse authResponse(String token, UserEntity user) {
        return AuthResponse.bearerToken(
                token,
                user.getEmail(),
                user.getFullName(),
                user.getRole(),
                user.getAccountType()
        );
    }

    private static String normalizeAndValidateEmail(String email) {
        String normalized = validateRequiredText(email, "email").toLowerCase(Locale.ROOT);
        if (!normalized.contains("@")) {
            throw new IllegalArgumentException("Email format is invalid");
        }
        return normalized;
    }

    private static String validateRequiredText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " is required");
        }
        return value.trim();
    }

    private static String buildFullName(String fullName, String email) {
        if (fullName != null && !fullName.isBlank()) {
            return fullName.trim();
        }
        return email.substring(0, email.indexOf('@'));
    }

    public record UserProfile(
            String email,
            String fullName,
            String role,
            String avatarUrl,
            String accountType,
            int totalXp,
            int currentStreak,
            int longestStreak
    ) {
    }

}
