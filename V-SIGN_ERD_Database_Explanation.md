# V-SIGN Database Explanation Through ERD

## 1. Scope and Source

This document explains the V-SIGN database based on:
- ERD image: `docs/V-SIGN_ERD.png`
- User stories and acceptance criteria: `docs/V-SIGN_UserStories_Full.docx` (May 2026)

The schema is organized into 5 bounded domains:
- User and Authorization
- Content Management
- Learning and Progress
- Gamification
- Monetization (tightly connected to User domain)

## 2. High-Level Entity Map

Core ownership starts from `Users`.

- `Users` 1-N `Subscriptions`
- `Users` 1-N `Payment_Transactions`
- `Subscriptions` 1-N `Payment_Transactions`
- `Units` 1-N `Chapters`
- `Chapters` 1-N `Lessons`
- `Lessons` 1-N `User_Progress`
- `Lessons` 1-N `Quizzes`
- `Quizzes` 1-N `Quiz_Questions`
- `Quiz_Questions` 1-N `Quiz_Options`
- `Quizzes` 1-N `Quiz_Attempts`
- `Quiz_Attempts` 1-N `AI_Attempts_Log`
- `Users` 1-N `User_Progress`
- `Users` 1-N `Quiz_Attempts`
- `Users` 1-N `AI_Attempts_Log`
- `Users` 1-1 `Streaks` (enforced by unique `user_id`)
- `Users` 1-N `XP_Logs`
- `Users` 1-N `Leaderboard_Snapshots`
- `Users` 1-N `User_Badges`
- `Badges` 1-N `User_Badges`

`Dictionary` is currently standalone (no FK to lessons/quizzes in ERD).

## 3. Domain-by-Domain Breakdown

### 3.1 User and Authorization

#### `Users`
Primary identity and access table.

Key fields:
- `email` unique login identifier
- `password_hash` for credential auth
- `account_type` controls free/premium access
- `is_active` supports account blocking

Supports US-01 to US-08, and drives authorization checks in Learning/AI Quiz/Paywall flows.

#### `Subscriptions`
Represents a user plan lifecycle.

Key fields:
- `plan_type` (`MONTHLY`, `YEARLY`)
- `status` (`ACTIVE`, `EXPIRED`, `CANCELLED`)
- `start_date`, `end_date`

Supports US-57, US-60, US-63, US-72.

#### `Payment_Transactions`
Stores payment requests and gateway callbacks.

Key fields:
- `provider` (`MOMO`, `ZALOPAY`)
- `provider_transaction_id` for reconciliation
- `status` (`PENDING`, `SUCCESS`, `FAILED`, `REFUNDED`)
- `raw_payload` for webhook debug/audit

Supports US-58 to US-64, US-73, US-74.

### 3.2 Content Management

#### `Units` -> `Chapters` -> `Lessons`
Defines the learning hierarchy.

- `Units`: top-level content module
- `Chapters`: grouped under units, include `is_premium` flag
- `Lessons`: actual learning item (video/interactive), includes `xp_reward`

Supports US-09 to US-18, US-65 to US-67.

#### `Dictionary`
VSL sign reference content.

Key fields:
- `sign_name`, `category`, `description`
- `video_url`, `thumbnail_url`
- `difficulty_level`

Supports US-50 to US-55 and US-70.

### 3.3 Learning and Assessment

#### `User_Progress`
Tracks per-user per-lesson state.

Key fields:
- `status` (`NOT_STARTED`, `IN_PROGRESS`, `COMPLETED`)
- `completion_pct`
- timestamps for started/completed/update

Supports US-14 to US-18 and progress-driven DAU metric in US-75.

#### `Quizzes`
Quiz config bound to a lesson.

Key fields:
- `quiz_type` (`MCQ`, `AI_SIGN`, `TIMED_TEST`)
- `passing_score`, `time_limit_seconds`
- `xp_reward`

Supports US-20 to US-32 and US-68 to US-69.

#### `Quiz_Questions` and `Quiz_Options`
Question bank design for MCQ and AI sign questions.

- `Quiz_Questions.question_type` distinguishes MCQ vs AI sign
- `sign_label` enables AI sign matching
- `Quiz_Options` contains selectable options for MCQ

Supports US-21, US-25, US-26, US-68, US-69.

#### `Quiz_Attempts`
Stores each user attempt and result state.

Key fields:
- `score`, `total_questions`, `correct_answers`
- `status` (`IN_PROGRESS`, `COMPLETED`, `TIMED_OUT`)

Supports immediate score, retry, timed auto-submit behaviors (US-20, US-22, US-24).

#### `AI_Attempts_Log`
Stores AI-sign inference logs per attempt/question.

Key fields:
- `predicted_sign`, `confidence`
- `is_correct`
- `landmark_data` JSON

Supports AI feedback and confidence tracking in US-28 to US-31.

### 3.4 Gamification

#### `XP_Logs`
Event ledger for XP credits.

Key fields:
- `source_type` (`LESSON_COMPLETE`, `QUIZ_COMPLETE`, `STREAK_BONUS`, `BADGE_EARN`)
- `source_id` (event reference)
- `xp_earned`

Supports US-33 to US-36, US-41.

#### `Streaks`
Current/longest streak state per user.

Key fields:
- unique `user_id` (one streak row per user)
- `current_streak`, `longest_streak`
- `last_activity_date`

Supports US-37 to US-40.

#### `Badges` and `User_Badges`
Badge catalog and awarding bridge.

- `Badges` defines rules (`condition_type`, `condition_value`)
- `User_Badges` records earned badges with timestamp

Supports US-45 to US-49.

#### `Leaderboard_Snapshots`
Persisted weekly/monthly ranking snapshots.

Key fields:
- `period_type` (`WEEKLY`, `MONTHLY`)
- `period_start`, `period_end`
- `total_xp`, `rank`

Supports US-42 to US-44.

## 4. How the ERD Supports Main Product Flows

### Learning flow
1. User opens `Units`.
2. User drills into `Chapters` and `Lessons`.
3. System updates `User_Progress` as lesson state changes.
4. Lesson completion can trigger `XP_Logs` entry.

### Quiz flow (MCQ/Timed/AI)
1. `Quizzes` attached to lesson.
2. Questions loaded from `Quiz_Questions` (+ `Quiz_Options` for MCQ).
3. Attempt stored in `Quiz_Attempts`.
4. AI-sign frames/results stored in `AI_Attempts_Log`.
5. Passing/complete events can credit XP and unlock next progress.

### Premium payment flow
1. User starts checkout -> create `Payment_Transactions` (PENDING).
2. Gateway webhook updates transaction status.
3. On SUCCESS, create/update `Subscriptions` and upgrade `Users.account_type`.
4. Access control checks `account_type` and premium chapter flags.

### Gamification flow
1. Learning/quiz events create `XP_Logs`.
2. Streak updates in `Streaks` based on daily activity.
3. Badge rules in `Badges` evaluated and persisted in `User_Badges`.
4. Ranking periods materialized in `Leaderboard_Snapshots`.

## 5. Important Design Notes Observed from Stories

- `XP_Logs.source_id` is polymorphic and does not enforce FK in ERD; application logic must validate consistency with `source_type`.
- `Dictionary` appears public in stories, but ERD has no explicit `is_published` field.
- `User_Progress` should practically enforce unique (`user_id`, `lesson_id`) to prevent duplicate progress rows.
- `User_Badges` should practically enforce unique (`user_id`, `badge_id`) for idempotent awards.
- Admin audit requirement in US-74 (`updated_by`) is not visible as a dedicated audit table in this ERD.

## 6. Table Inventory Summary

Total entities in ERD: 18

- User/Auth + Monetization: `Users`, `Subscriptions`, `Payment_Transactions`
- Content: `Units`, `Chapters`, `Lessons`, `Dictionary`
- Learning/Assessment: `User_Progress`, `Quizzes`, `Quiz_Attempts`, `Quiz_Questions`, `Quiz_Options`, `AI_Attempts_Log`
- Gamification: `XP_Logs`, `Streaks`, `Badges`, `User_Badges`, `Leaderboard_Snapshots`

This model is generally aligned with the 7-epic backlog and supports the required MVP behaviors around learning progression, assessments, premium gating, payments, and gamification.
