# V-SIGN User Stories vs Frontend Verification Report

## Verification Scope
- **Document source:** `V-SIGN_UserStories_Full.docx`
- **Frontend evidence source:** **Image #1** (Learning flow), **Image #2** (ERD), **Image #3** (Use Case diagram)
- **Method:** Extracted all **75 US** and mapped each against visible frontend workflows/components in the images.

## 1. VERIFICATION AND MAPPING TABLE

### Epic 1: Authentication & Profile
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-01** | Đăng ký tài khoản | **Matched** | Image #3 has `Đăng ký tài khoản`. |
| **US-02** | Đăng nhập | **Matched** | Image #3 has `Đăng nhập`. |
| **US-03** | Đăng nhập Google OAuth | **Matched** | Image #3 has `Đăng nhập Google OAuth`. |
| **US-04** | Xem Profile | **Matched** | Image #3 has `Xem & Chỉnh sửa Profile`. |
| **US-05** | Chỉnh sửa Profile | **Matched** | Image #3 has `Xem & Chỉnh sửa Profile`. |
| **US-06** | Đổi mật khẩu | **Matched** | Image #3 has `Đổi mật khẩu`. |
| **US-07** | Đăng xuất | **Gap** | No explicit logout flow shown in Image #1/#3. |
| **US-08** | Quên mật khẩu | **Gap** | No password-reset flow shown in Image #1/#3. |

### Epic 2: Content Management (Learning)
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-09** | Xem danh sách Unit | **Matched** | Image #1 Step 1: `Load danh sách Unit`. |
| **US-10** | Xem Chapter trong Unit | **Matched** | Image #1 Step 2: `GET /api/units/{unitId}/chapters`. |
| **US-11** | Hiển thị khóa Chapter Premium | **Matched** | Image #1 Step 2: `is_premium=TRUE` + Basic user -> lock icon. |
| **US-12** | Xem danh sách Lesson | **Matched** | Image #1 Step 2: `GET /api/chapters/{chapterId}/lessons`. |
| **US-13** | Xem video bài học | **Matched** | Image #1 Step 3: S3 presigned URL streaming. |
| **US-14** | Lưu tiến độ tự động | **Matched** | Image #1 Step 4: `/api/progress/start` and `/complete`. |
| **US-15** | Progress bar theo Chapter | **Matched** | Image #1 chapter payload includes progress status for rendering progress. |
| **US-16** | Mở khóa bài học kế tiếp | **Matched** | Image #1 Step 5: `unlock-status` logic. |
| **US-17** | Redirect Paywall khi click Chapter Premium | **Mismatched** | US says redirect page; Image #1 shows **paywall modal overlay**. |
| **US-18** | Resume bài học dang dở | **Gap** | No explicit resume-from-last-position flow in Image #1/#3. |

### Epic 3: Assessment
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-19** | Làm Quiz MCQ | **Matched** | Image #3 has `Làm Quiz MCQ`. |
| **US-20** | Xem điểm sau Quiz | **Matched** | Image #3 has `Xem kết quả & điểm số`. |
| **US-21** | Review đáp án | **Gap** | Image #3 does not show detailed review screen/flow. |
| **US-22** | Làm lại Quiz | **Gap** | Retake flow not shown in Image #3. |
| **US-23** | Thi thử có bấm giờ | **Matched** | Image #3 has `Thi thử có bấm giờ`. |
| **US-24** | Auto-submit khi hết giờ | **Gap** | Auto-submit rule not shown in Image #3 flow. |
| **US-25** | Điều hướng tự do giữa các câu | **Gap** | Free question navigation not shown in Image #3. |
| **US-26** | Cảnh báo câu chưa trả lời | **Gap** | Unanswered warning dialog not shown in Image #3. |
| **US-27** | Luyện tập AI (AI Quiz) | **Matched** | Image #3 has `Luyện tập AI` + camera steps. |
| **US-28** | Privacy-safe AI (no video to server) | **Matched** | Image #3 shows `MediaPipe Engine (Client-side AI)` external. |
| **US-29** | Thanh confidence real-time | **Gap** | Confidence indicator not shown in Image #3. |
| **US-30** | Phản hồi tức thì sau mỗi ký hiệu | **Gap** | Explicit instant correctness feedback flow not shown in Image #3. |
| **US-31** | Cảnh báo ánh sáng yếu | **Gap** | No low-light warning branch shown in Image #3. |
| **US-32** | Preview AI Quiz cho Basic User | **Matched** | Premium gate/paywall path shown in Image #1/#3. |

### Epic 4: Gamification
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-33** | Nhận XP sau bài học | **Matched** | Image #1 Step 4 shows `INSERT XP_Logs`. |
| **US-34** | Nhận XP sau Quiz | **Gap** | Image flows explicitly show lesson-complete XP, not quiz XP trigger. |
| **US-35** | Animation XP pop-up | **Gap** | XP animation behavior not explicit in Image #1/#3. |
| **US-36** | Tổng XP trên Profile | **Matched** | Gamification profile view exists in Image #3. |
| **US-37** | Xây dựng Streak hàng ngày | **Matched** | Image #1 Step 4 updates streak; Image #3 has `Cập Nhật Streak`. |
| **US-38** | Hiển thị Streak tăng | **Gap** | Streak increase animation not shown in images. |
| **US-39** | Thông báo reset Streak rõ ràng | **Gap** | Reset message/tooltip flow not shown in images. |
| **US-40** | Longest Streak trên Profile | **Gap** | Longest streak field/display not shown in images. |
| **US-41** | XP Multiplier theo Streak Milestone | **Gap** | Multiplier rules not shown in images. |
| **US-42** | Bảng xếp hạng tuần | **Matched** | Image #3 has `Xem Bảng xếp hạng`. |
| **US-43** | Bảng xếp hạng tháng | **Gap** | Monthly-specific leaderboard tab not shown in images. |
| **US-44** | Highlight vị trí của mình | **Gap** | Self-highlight/pinned row not shown in images. |
| **US-45** | Badge cho Streak Milestone | **Matched** | Image #3: `Cập Nhật Streak` extends to `Nhận Huy hiệu`. |
| **US-46** | Badge cho XP Milestone | **Gap** | XP-milestone badge rule not shown in images. |
| **US-47** | Badge bài học đầu tiên | **Gap** | First-lesson badge trigger not shown in images. |
| **US-48** | Badge điểm tuyệt đối | **Gap** | Perfect-score badge trigger not shown in images. |
| **US-49** | Xem tất cả Badge trên Profile | **Matched** | Profile gamification view exists in Image #3. |

### Epic 5: VSL Dictionary
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-50** | Duyệt từ điển không cần đăng nhập | **Matched** | Image #3 shows Guest can access Dictionary use cases. |
| **US-51** | Lọc từ điển theo danh mục | **Matched** | Image #3 has `Duyệt từ điển theo danh mục`. |
| **US-52** | Tìm kiếm từ điển | **Matched** | Image #3 has `Tìm kiếm từ điển`. |
| **US-53** | Xem video minh họa ký hiệu | **Matched** | Image #3 has `Xem chi tiết từ (Video)`. |
| **US-54** | Mức độ khó của ký hiệu | **Gap** | Difficulty level/filter not shown in dictionary flows. |
| **US-55** | Nút "Luyện tập ngay" từ Từ điển | **Gap** | No dictionary -> AI practice CTA shown in Image #3. |

### Epic 6: Monetization
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-56** | Hiển thị Paywall | **Matched** | Image #1 and #3 both show paywall flow. |
| **US-57** | Xem gói Premium | **Matched** | Image #3 has `Chọn gói Premium`. |
| **US-58** | Thanh toán MoMo QR | **Matched** | Image #3 has `Thanh toán QR (MoMo/Zalo)`. |
| **US-59** | Thanh toán ZaloPay QR | **Matched** | Image #3 has `Thanh toán QR (MoMo/Zalo)`. |
| **US-60** | Nâng cấp tài khoản ngay sau thanh toán | **Matched** | Image #3: webhook confirmation -> upgrade account type. |
| **US-61** | Màn hình xác nhận thanh toán | **Gap** | Explicit post-success confirmation screen not shown in images. |
| **US-62** | Thông báo lỗi thanh toán | **Gap** | Payment failure UX branch not shown in images. |
| **US-63** | Xem trạng thái Subscription | **Gap** | Subscription expiry/status display flow not shown in images. |
| **US-64** | Lịch sử thanh toán | **Matched** | Image #3 has `Xem lịch sử thanh toán`. |

### Epic 7: Admin Panel
| US ID | US Name | Status | Details of Inconsistency (Evidence from US vs. Frontend Images) |
|---|---|---|---|
| **US-65** | Quản lý Unit | **Gap** | No Admin actor/panel in Image #3. |
| **US-66** | Quản lý Chapter & is_premium | **Gap** | No Admin management workflow shown. |
| **US-67** | Upload video bài học lên S3 | **Gap** | No Admin upload UI flow shown. |
| **US-68** | Tạo câu hỏi MCQ | **Gap** | No Admin MCQ authoring flow shown. |
| **US-69** | Tạo câu hỏi AI Quiz | **Gap** | No Admin AI-question authoring flow shown. |
| **US-70** | Quản lý Từ điển VSL | **Gap** | No Admin dictionary CRUD flow shown. |
| **US-71** | Xem danh sách người dùng | **Gap** | No Admin user-list flow shown. |
| **US-72** | Xem subscription status của user | **Gap** | No Admin subscription detail flow shown. |
| **US-73** | Xem toàn bộ giao dịch thanh toán | **Gap** | No Admin transaction-monitoring flow shown. |
| **US-74** | Thủ công cập nhật trạng thái giao dịch | **Gap** | No manual override flow shown. |
| **US-75** | Dashboard KPI Admin | **Gap** | No Admin KPI dashboard shown. |

## 2. PROPOSED USER STORIES MODIFICATIONS

- **US-07: Đăng xuất**
  - *Old content in document:*
    ```text
    Story: As a User, I want to log out, so that my account is safe on shared devices.
    ```
  - *Reason for modification:* No logout flow appears in current frontend images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-08: Quên mật khẩu**
  - *Old content in document:*
    ```text
    Story: As a Guest, I want to request a password reset via email, so that I can recover my account.
    ```
  - *Reason for modification:* Reset-password workflow is not shown in frontend images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-17: Redirect Paywall khi click Chapter Premium**
  - *Old content in document:*
    ```text
    Story: ...redirected to the upgrade page when clicking a locked Chapter...
    ```
  - *Reason for modification:* Image #1 shows paywall as modal overlay, not full-page redirect.
  - *Proposed new content:*
    ```text
    Story: As a Basic User, I want a paywall modal to appear when clicking a locked Premium Chapter, so that I can upgrade without losing my current context.
    AC1: Basic User clicking a premium chapter -> paywall modal appears.
    ```

- **US-18: Resume bài học dang dở**
  - *Old content in document:*
    ```text
    Story: As a User, I want to resume a lesson I didn't finish...
    ```
  - *Reason for modification:* Resume behavior is not explicit in image flows.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-21: Review đáp án**
  - *Old content in document:*
    ```text
    Story: As a User, I want to review correct and incorrect answers after finishing a quiz...
    ```
  - *Reason for modification:* Review-detail flow is absent in Image #3.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-22: Làm lại Quiz**
  - *Old content in document:*
    ```text
    Story: As a User, I want to retake a quiz I failed...
    ```
  - *Reason for modification:* Retake loop is not shown in current frontend images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-24: Auto-submit khi hết giờ**
  - *Old content in document:*
    ```text
    Story: As a User, I want my quiz to auto-submit when the timer runs out...
    ```
  - *Reason for modification:* Timer-expiry auto-submit behavior is not explicit in image flow.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-25: Điều hướng tự do giữa các câu**
  - *Old content in document:*
    ```text
    Story: As a User, I want to navigate between questions freely during a timed test...
    ```
  - *Reason for modification:* Question navigator behavior is not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-26: Cảnh báo câu chưa trả lời**
  - *Old content in document:*
    ```text
    Story: As a User, I want to see which questions I haven't answered before submitting...
    ```
  - *Reason for modification:* Unanswered-warning dialog is not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-29: Thanh confidence real-time**
  - *Old content in document:*
    ```text
    Story: ...see a confidence indicator while performing a sign...
    ```
  - *Reason for modification:* No confidence bar/state in image evidence.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v2 (AI quality enhancements)
    ```

- **US-30: Phản hồi tức thì sau mỗi ký hiệu**
  - *Old content in document:*
    ```text
    Story: ...get instant feedback (correct/incorrect) after each sign attempt...
    ```
  - *Reason for modification:* Per-attempt feedback state is not explicit in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v2
    ```

- **US-31: Cảnh báo ánh sáng yếu**
  - *Old content in document:*
    ```text
    Story: ...warning when lighting is too low for hand detection...
    ```
  - *Reason for modification:* Low-light detection branch absent in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v2
    ```

- **US-34: Nhận XP sau Quiz**
  - *Old content in document:*
    ```text
    Story: As a User, I want to earn XP when I complete a quiz...
    ```
  - *Reason for modification:* Image #1 explicitly shows XP insertion on lesson completion, not quiz completion.
  - *Proposed new content:*
    ```text
    Priority: 🟡 Should Have
    Target Release: v1.1
    ```

- **US-35: Animation XP pop-up**
  - *Old content in document:*
    ```text
    Story: ...see an XP animation pop up after completing activities...
    ```
  - *Reason for modification:* Animation behavior not represented in image flows.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1 (UI polish)
    ```

- **US-38: Hiển thị Streak tăng**
  - *Old content in document:*
    ```text
    Story: ...streak to visually increase when learning consecutive days...
    ```
  - *Reason for modification:* No streak animation shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-39: Thông báo reset Streak rõ ràng**
  - *Old content in document:*
    ```text
    Story: ...clearly see when my streak resets...
    ```
  - *Reason for modification:* Reset-message UX is absent in image flows.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-40: Longest Streak trên Profile**
  - *Old content in document:*
    ```text
    Story: ...want my longest streak to be recorded on my profile...
    ```
  - *Reason for modification:* Longest streak display not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-41: XP Multiplier theo Streak Milestone**
  - *Old content in document:*
    ```text
    Story: ...bonus XP multipliers at streak milestones...
    ```
  - *Reason for modification:* Multiplier logic is not represented in current flows.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v2
    ```

- **US-43: Bảng xếp hạng tháng**
  - *Old content in document:*
    ```text
    Story: As a User, I want to see a monthly leaderboard.
    ```
  - *Reason for modification:* Only generic leaderboard is shown; no monthly tab.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-44: Highlight vị trí của mình**
  - *Old content in document:*
    ```text
    Story: ...see my own rank highlighted on the leaderboard...
    ```
  - *Reason for modification:* Self-highlight/pinned-row behavior not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-46: Badge cho XP Milestone**
  - *Old content in document:*
    ```text
    Story: ...earn a badge when I reach XP milestones...
    ```
  - *Reason for modification:* XP milestone badge trigger not explicit in current image flows.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-47: Badge bài học đầu tiên**
  - *Old content in document:*
    ```text
    Story: ...earn a badge for completing my first lesson...
    ```
  - *Reason for modification:* No first-lesson badge trigger shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-48: Badge điểm tuyệt đối**
  - *Old content in document:*
    ```text
    Story: ...earn a badge for getting a perfect score on a quiz...
    ```
  - *Reason for modification:* Perfect-score badge rule is not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-54: Mức độ khó của ký hiệu**
  - *Old content in document:*
    ```text
    Story: ...see a difficulty level for each sign...
    ```
  - *Reason for modification:* Dictionary flow in images does not include difficulty filter/labels.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1 (Dictionary enhancement)
    ```

- **US-55: Nút "Luyện tập ngay" từ Từ điển**
  - *Old content in document:*
    ```text
    Story: ...a "Practice this sign" button on dictionary entries...
    ```
  - *Reason for modification:* No dictionary-to-practice transition is shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-61: Màn hình xác nhận thanh toán**
  - *Old content in document:*
    ```text
    Story: ...see a payment confirmation screen after success...
    ```
  - *Reason for modification:* Confirmation-screen state is not explicit in monetization image flow.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1 (Payment UX)
    ```

- **US-62: Thông báo lỗi thanh toán**
  - *Old content in document:*
    ```text
    Story: ...see a clear error message if my payment fails...
    ```
  - *Reason for modification:* Failure-state UX is not shown in images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-63: Xem trạng thái Subscription**
  - *Old content in document:*
    ```text
    Story: ...view my subscription status and expiry date...
    ```
  - *Reason for modification:* Subscription-expiry display is not shown in current frontend images.
  - *Proposed new content:*
    ```text
    Priority: ⚪ Won't Have (v1)
    Target Release: v1.1
    ```

- **US-65: Quản lý Unit**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to create, edit, and delete Units.
    ```
  - *Reason for modification:* Admin module is not present in frontend image scope.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-66: Quản lý Chapter & is_premium**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to create Chapters and set is_premium...
    ```
  - *Reason for modification:* No admin content-management flow shown in images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-67: Upload video bài học lên S3**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to upload lesson videos to S3...
    ```
  - *Reason for modification:* Admin upload UI is absent in current frontend images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-68: Tạo câu hỏi MCQ**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to create MCQ quiz questions...
    ```
  - *Reason for modification:* Admin quiz authoring flow is not represented in images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-69: Tạo câu hỏi AI Quiz**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to create AI Quiz questions by specifying sign_label.
    ```
  - *Reason for modification:* Admin AI-question setup not shown in images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-70: Quản lý Từ điển VSL**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to add and edit Dictionary entries.
    ```
  - *Reason for modification:* No admin dictionary CRUD flow in frontend images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-71: Xem danh sách người dùng**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to view a list of all registered users.
    ```
  - *Reason for modification:* Admin user-management flow absent in images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-72: Xem subscription status của user**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to see each user's subscription status.
    ```
  - *Reason for modification:* No admin subscription detail flow shown.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-73: Xem toàn bộ giao dịch thanh toán**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to view all payment transactions with status.
    ```
  - *Reason for modification:* Admin transaction-monitoring UI absent in image evidence.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-74: Thủ công cập nhật trạng thái giao dịch**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want to manually trigger a subscription update...
    ```
  - *Reason for modification:* Manual override flow is not shown in current frontend images.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Panel - Phase 2 Backlog"
    Priority: ⚪ Won't Have (v1)
    ```

- **US-75: Dashboard KPI Admin**
  - *Old content in document:*
    ```text
    Story: As an Admin, I want a dashboard with total users, active subscriptions, and DAU.
    ```
  - *Reason for modification:* Admin KPI dashboard not represented in image scope.
  - *Proposed new content:*
    ```text
    Scope: Move to "Admin Analytics Module - Phase 2"
    Priority: ⚪ Won't Have (v1)
    ```

## Summary
- **Matched:** 36
- **Mismatched:** 1
- **Gap:** 38
- **Main issue:** Document scope is broader than current frontend image scope, especially for **Admin**, **advanced assessment**, and **advanced gamification/payment UX** features.
