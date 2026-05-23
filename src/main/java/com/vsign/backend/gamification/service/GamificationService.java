package com.vsign.backend.gamification.service;

import com.vsign.backend.common.exception.BusinessException;
import com.vsign.backend.common.exception.ErrorCode;
import com.vsign.backend.gamification.dto.BadgeResponse;
import com.vsign.backend.gamification.dto.LeaderboardEntryResponse;
import com.vsign.backend.gamification.dto.LeaderboardPeriod;
import com.vsign.backend.gamification.dto.LeaderboardRequest;
import com.vsign.backend.gamification.dto.LeaderboardResponse;
import com.vsign.backend.gamification.dto.UserProgressSummaryResponse;
import com.vsign.backend.gamification.dto.XpAwardRequest;
import com.vsign.backend.gamification.dto.XpAwardResponse;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class GamificationService {

    private static final String GENERATED_FROM = "in-memory-ledger";
    private static final int MAX_PAGE_SIZE = 50;
    private static final int LEVEL_XP_STEP = 200;

    private final Map<String, UserState> users = new HashMap<>();
    private final Clock clock;

    public GamificationService() {
        this(Clock.systemUTC());
    }

    GamificationService(Clock clock) {
        this.clock = clock;
        seedUsers();
    }

    public UserProgressSummaryResponse getSummaryByEmail(String email) {
        return toSummary(resolveUserByEmail(email));
    }

    public LeaderboardResponse getLeaderboard(LeaderboardRequest request, String currentUserEmail) {
        LeaderboardPeriod period = parsePeriod(request.period());
        int page = requirePage(request.page());
        int size = requireSize(request.size());
        List<LeaderboardEntryResponse> rankedEntries = rankedEntries(period);
        LeaderboardEntryResponse currentUser = findCurrentUser(currentUserEmail, rankedEntries);
        int fromIndex = Math.min(page * size, rankedEntries.size());
        int toIndex = Math.min(fromIndex + size, rankedEntries.size());
        int totalPages = rankedEntries.isEmpty() ? 0 : (int) Math.ceil((double) rankedEntries.size() / size);

        return new LeaderboardResponse(
                GENERATED_FROM,
                period,
                page,
                size,
                rankedEntries.size(),
                totalPages,
                rankedEntries.subList(fromIndex, toIndex),
                currentUser
        );
    }

    public XpAwardResponse awardXp(String email, XpAwardRequest request) {
        UserState user = resolveUserByEmail(email);
        String eventId = requireText(request.eventId(), "eventId");
        String source = requireText(request.source(), "source");
        int xpDelta = request.xpDelta();
        if (xpDelta <= 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "xpDelta must be greater than zero");
        }

        if (user.processedEventIds.contains(eventId)) {
            return new XpAwardResponse(eventId, true, toSummary(user));
        }

        user.processedEventIds.add(eventId);
        user.totalXp += xpDelta;
        user.completedLessons += source.equalsIgnoreCase("LESSON_COMPLETE") ? 1 : 0;
        user.completedAssessments += source.equalsIgnoreCase("QUIZ_COMPLETE") ? 1 : 0;
        updateStreak(user, request.activityDate());
        awardBadges(user);

        return new XpAwardResponse(eventId, false, toSummary(user));
    }

    private UserState resolveUserByEmail(String email) {
        String normalized = requireText(email, "authenticated email").toLowerCase();
        UserState user = users.get(normalized);
        if (user == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND, "Gamification user not found");
        }
        return user;
    }

    private void seedUsers() {
        users.put("learner.one@vsign.test", new UserState(
                "learner-001", "learner.one@vsign.test", "Learner One", 720, 6, 9, 18, 4,
                List.of(badge("first-lesson", "First Lesson", "Completed the first V-Sign lesson", "2026-05-01"))
        ));
        users.put("learner.two@vsign.test", new UserState(
                "learner-002", "learner.two@vsign.test", "Learner Two", 940, 4, 12, 24, 6,
                List.of(badge("dictionary-explorer", "Dictionary Explorer", "Reviewed 20 dictionary signs", "2026-05-11"))
        ));
        users.put("learner.three@vsign.test", new UserState(
                "learner-003", "learner.three@vsign.test", "Learner Three", 1180, 9, 15, 31, 8,
                List.of(badge("streak-7", "Seven Day Streak", "Practiced for seven consecutive days", "2026-05-10"))
        ));
    }

    private static LeaderboardPeriod parsePeriod(String period) {
        try {
            return LeaderboardPeriod.valueOf(period == null ? "WEEKLY" : period.trim().toUpperCase());
        } catch (IllegalArgumentException exception) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "period must be WEEKLY or MONTHLY");
        }
    }

    private static int requirePage(Integer page) {
        if (page == null || page < 0) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "page must be greater than or equal to zero");
        }
        return page;
    }

    private static int requireSize(Integer size) {
        if (size == null || size <= 0 || size > MAX_PAGE_SIZE) {
            throw new BusinessException(ErrorCode.VALIDATION_ERROR, "size must be between 1 and 50");
        }
        return size;
    }

    private static String requireText(String value, String field) {
        if (value == null || value.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, field + " is required");
        }
        return value.trim();
    }

    private static BadgeResponse badge(String id, String name, String description, String earnedAt) {
        return new BadgeResponse(id, name, description, LocalDate.parse(earnedAt));
    }

    private List<LeaderboardEntryResponse> rankedEntries(LeaderboardPeriod period) {
        List<UserState> usersByScore = users.values().stream()
                .sorted(Comparator.comparingInt((UserState user) -> scoreFor(period, user)).reversed())
                .toList();
        List<LeaderboardEntryResponse> entries = new ArrayList<>();
        for (int i = 0; i < usersByScore.size(); i++) {
            UserState user = usersByScore.get(i);
            entries.add(new LeaderboardEntryResponse(
                    i + 1,
                    user.userId,
                    user.displayName,
                    scoreFor(period, user),
                    user.totalXp,
                    levelFromXp(user.totalXp),
                    user.currentStreakDays
            ));
        }
        return entries;
    }

    private LeaderboardEntryResponse findCurrentUser(String currentUserEmail, List<LeaderboardEntryResponse> rankedEntries) {
        if (currentUserEmail == null || currentUserEmail.isBlank()) {
            return null;
        }
        UserState user = resolveUserByEmail(currentUserEmail);
        return rankedEntries.stream()
                .filter(entry -> entry.userId().equals(user.userId))
                .findFirst()
                .orElse(null);
    }

    private static int scoreFor(LeaderboardPeriod period, UserState user) {
        if (period == LeaderboardPeriod.MONTHLY) {
            return user.totalXp;
        }
        return Math.max(0, user.totalXp / 3 + user.currentStreakDays * 10);
    }

    private void updateStreak(UserState user, String requestedDate) {
        LocalDate activityDate = requestedDate == null || requestedDate.isBlank()
                ? LocalDate.now(clock)
                : LocalDate.parse(requestedDate);
        if (user.lastActivityDate == null) {
            user.lastActivityDate = activityDate;
            user.currentStreakDays = Math.max(1, user.currentStreakDays);
        } else {
            long daysGap = ChronoUnit.DAYS.between(user.lastActivityDate, activityDate);
            if (daysGap == 0) {
                return;
            }
            if (daysGap == 1) {
                user.currentStreakDays += 1;
                user.lastActivityDate = activityDate;
            } else if (daysGap > 1) {
                user.currentStreakDays = 1;
                user.lastActivityDate = activityDate;
            }
        }
        user.longestStreakDays = Math.max(user.longestStreakDays, user.currentStreakDays);
    }

    private void awardBadges(UserState user) {
        if (user.completedLessons >= 1) {
            upsertBadge(user, badge("first-lesson", "First Lesson", "Completed the first V-Sign lesson", LocalDate.now(clock).toString()));
        }
        if (user.currentStreakDays >= 7) {
            upsertBadge(user, badge("streak-7", "Seven Day Streak", "Practiced for seven consecutive days", LocalDate.now(clock).toString()));
        }
        if (user.completedAssessments >= 1) {
            upsertBadge(user, badge("assessment-pass", "Assessment Pass", "Completed a quiz assessment", LocalDate.now(clock).toString()));
        }
    }

    private static void upsertBadge(UserState user, BadgeResponse badge) {
        boolean exists = user.badges.stream().anyMatch(existing -> existing.id().equals(badge.id()));
        if (!exists) {
            user.badges.add(badge);
        }
    }

    private static UserProgressSummaryResponse toSummary(UserState user) {
        int level = levelFromXp(user.totalXp);
        int nextLevelXp = (level + 1) * LEVEL_XP_STEP;
        return new UserProgressSummaryResponse(
                user.userId,
                user.displayName,
                level,
                user.totalXp,
                user.currentStreakDays,
                user.longestStreakDays,
                nextLevelXp,
                user.completedLessons,
                user.completedAssessments,
                List.copyOf(user.badges)
        );
    }

    private static int levelFromXp(int totalXp) {
        return Math.max(1, totalXp / LEVEL_XP_STEP);
    }

    private static final class UserState {
        private final String userId;
        private final String email;
        private final String displayName;
        private int totalXp;
        private int currentStreakDays;
        private int longestStreakDays;
        private int completedLessons;
        private int completedAssessments;
        private LocalDate lastActivityDate = LocalDate.now(ZoneOffset.UTC).minusDays(1);
        private final List<BadgeResponse> badges;
        private final Set<String> processedEventIds = new HashSet<>();

        private UserState(
                String userId,
                String email,
                String displayName,
                int totalXp,
                int currentStreakDays,
                int longestStreakDays,
                int completedLessons,
                int completedAssessments,
                List<BadgeResponse> badges
        ) {
            this.userId = userId;
            this.email = email;
            this.displayName = displayName;
            this.totalXp = totalXp;
            this.currentStreakDays = currentStreakDays;
            this.longestStreakDays = longestStreakDays;
            this.completedLessons = completedLessons;
            this.completedAssessments = completedAssessments;
            this.badges = new ArrayList<>(badges);
        }
    }
}
