package com.vsign.backend.gamification.controller;

import com.vsign.backend.common.response.SuccessResponse;
import com.vsign.backend.common.security.JwtAuthFilter;
import com.vsign.backend.gamification.dto.LeaderboardRequest;
import com.vsign.backend.gamification.dto.LeaderboardResponse;
import com.vsign.backend.gamification.service.GamificationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/leaderboards")
public class LeaderboardController {

    private final GamificationService gamificationService;

    public LeaderboardController(GamificationService gamificationService) {
        this.gamificationService = gamificationService;
    }

    @GetMapping
    public SuccessResponse<LeaderboardResponse> getLeaderboard(
            @RequestParam(defaultValue = "WEEKLY") String period,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String userId,
            HttpServletRequest httpRequest
    ) {
        LeaderboardRequest leaderboardRequest = new LeaderboardRequest(period, page, size, userId);
        String email = (String) httpRequest.getAttribute(JwtAuthFilter.AUTH_EMAIL_ATTRIBUTE);
        return SuccessResponse.ok("Leaderboard retrieved", gamificationService.getLeaderboard(leaderboardRequest, email));
    }
}
