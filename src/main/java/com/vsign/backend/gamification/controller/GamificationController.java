package com.vsign.backend.gamification.controller;

import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import com.vsign.backend.gamification.dto.LeaderboardResponse;
import com.vsign.backend.gamification.dto.UserProgressSummaryResponse;
import com.vsign.backend.gamification.dto.XpAwardRequest;
import com.vsign.backend.gamification.dto.XpAwardResponse;
import com.vsign.backend.gamification.service.GamificationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gamification")
public class GamificationController {

    private final GamificationService gamificationService;

    public GamificationController(GamificationService gamificationService) {
        this.gamificationService = gamificationService;
    }

    @GetMapping("/summary")
    public SuccessResponse<UserProgressSummaryResponse> getSummary(HttpServletRequest request) {
        String email = (String) request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok("Gamification summary retrieved", gamificationService.getSummaryByEmail(email));
    }

    @GetMapping("/leaderboard")
    public SuccessResponse<LeaderboardResponse> getLeaderboard(HttpServletRequest request) {
        String email = (String) request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok(
                "Leaderboard retrieved",
                gamificationService.getLeaderboard(new com.vsign.backend.gamification.dto.LeaderboardRequest("MONTHLY", 0, 10, null), email)
        );
    }

    @PostMapping("/xp-awards")
    public SuccessResponse<XpAwardResponse> awardXp(
            @Valid @RequestBody XpAwardRequest requestBody,
            HttpServletRequest request
    ) {
        String email = (String) request.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok("XP awarded", gamificationService.awardXp(email, requestBody));
    }
}
