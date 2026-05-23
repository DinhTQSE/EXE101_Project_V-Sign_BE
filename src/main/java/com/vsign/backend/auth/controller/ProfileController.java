package com.vsign.backend.auth.controller;

import com.vsign.backend.auth.dto.ProfileResponse;
import com.vsign.backend.auth.dto.UpdateProfileRequest;
import com.vsign.backend.auth.service.ProfileService;
import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public SuccessResponse<ProfileResponse> getProfile(HttpServletRequest request) {
        String email = authenticatedEmail(request);
        return SuccessResponse.ok("Profile retrieved", profileService.getProfile(email));
    }

    @PatchMapping("/profile")
    public SuccessResponse<ProfileResponse> updateProfile(
            HttpServletRequest request,
            @Valid @RequestBody UpdateProfileRequest updateRequest
    ) {
        String email = authenticatedEmail(request);
        return SuccessResponse.ok("Profile updated", profileService.updateProfile(email, updateRequest));
    }

    private String authenticatedEmail(HttpServletRequest request) {
        Object email = request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        if (email == null) {
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }
        return email.toString();
    }
}
