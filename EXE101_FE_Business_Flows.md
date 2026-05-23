# Frontend Business Flow Analysis

- Generated at: 2026-05-20T04:29:28.983Z
- Source root: `src`
- Flows detected: **146**
- Trace depth: 4

## Flow Index

1. `src/components/LessonModal.tsx:159` - onClick -> `() => handleSelect(idx)`
2. `src/components/LessonModal.tsx:196` - onClick -> `onCorrect`
3. `src/components/LessonModal.tsx:241` - onStatusChange -> `handleCamStatus`
4. `src/components/LessonModal.tsx:255` - onClick -> `handleScan`
5. `src/components/LessonModal.tsx:279` - onClick -> `onNextLesson`
6. `src/components/LessonModal.tsx:283` - onClick -> `onFinish`
7. `src/components/LessonModal.tsx:332` - onClick -> `onClose`
8. `src/components/LessonModal.tsx:383` - onClick -> `() => setStep(2)`
9. `src/components/LessonModal.tsx:410` - onCorrect -> `handlePracticeComplete`
10. `src/components/LessonModal.tsx:415` - onSuccess -> `handlePracticeComplete`
11. `src/components/LessonModal.tsx:429` - onFinish -> `handleFinish`
12. `src/components/LessonModal.tsx:429` - onNextLesson -> `onNextLesson`
13. `src/components/LessonView.tsx:66` - onClick -> `() => handleSelect(idx)`
14. `src/components/LessonView.tsx:118` - onChange -> `e => setAnswer(e.target.value)`
15. `src/components/LessonView.tsx:119` - onKeyDown -> `e => e.key === "Enter" && handleSubmit()`
16. `src/components/LessonView.tsx:123` - onClick -> `handleSubmit`
17. `src/components/LessonView.tsx:197` - onClick -> `startCamera`
18. `src/components/LessonView.tsx:201` - onClick -> `handleRecord`
19. `src/components/LessonView.tsx:293` - onClick -> `() => setSpeedMenuOpen(!speedMenuOpen)`
20. `src/components/LessonView.tsx:311` - onClick -> `() => { setPlaybackSpeed(speed); setSpeedMenuOpen(false);`
21. `src/components/LessonView.tsx:333` - onComplete -> `handleQuizComplete`
22. `src/components/LessonView.tsx:336` - onComplete -> `handleQuizComplete`
23. `src/components/LessonView.tsx:339` - onComplete -> `handleQuizComplete`
24. `src/components/LessonView.tsx:363` - onClick -> `handleNext`
25. `src/components/LoginModal.tsx:38` - onClick -> `onClose`
26. `src/components/LoginModal.tsx:45` - onClick -> `e => e.stopPropagation()`
27. `src/components/LoginModal.tsx:51` - onClick -> `onClose`
28. `src/components/LoginModal.tsx:56` - onSubmit -> `handleSubmit`
29. `src/components/LoginModal.tsx:61` - onChange -> `e => setName(e.target.value)`
30. `src/components/LoginModal.tsx:70` - onChange -> `e => setEmail(e.target.value)`
31. `src/components/LoginModal.tsx:91` - onClick -> `() => setIsSignUp(!isSignUp)`
32. `src/components/PracticeView.tsx:95` - onClick -> `checkSign`
33. `src/components/PremiumModal.tsx:75` - onClick -> `handleClose`
34. `src/components/PremiumModal.tsx:82` - onClick -> `e => e.stopPropagation()`
35. `src/components/PremiumModal.tsx:100` - onClick -> `handleClose`
36. `src/components/PremiumModal.tsx:108` - onClick -> `handleClose`
37. `src/components/PremiumModal.tsx:134` - onClick -> `() => setMethod("ewallet")`
38. `src/components/PremiumModal.tsx:151` - onClick -> `() => setMethod("card")`
39. `src/components/PremiumModal.tsx:174` - onChange -> `e => setCardNumber(e.target.value)`
40. `src/components/PremiumModal.tsx:177` - onChange -> `e => setCardName(e.target.value)`
41. `src/components/PremiumModal.tsx:181` - onChange -> `e => setCardExpiry(e.target.value)`
42. `src/components/PremiumModal.tsx:184` - onChange -> `e => setCardCvv(e.target.value)`
43. `src/components/PremiumModal.tsx:195` - onClick -> `() => setMethod("qr")`
44. `src/components/PremiumModal.tsx:237` - onClick -> `handlePay`
45. `src/components/QuizView.tsx:61` - onClick -> `() => { setQIdx(0); setSelected(null); setScore(0); setFinished(false);`
46. `src/components/QuizView.tsx:119` - onClick -> `() => handleAnswer(idx)`
47. `src/components/ui/carousel.tsx:120` - onKeyDownCapture -> `handleKeyDown`
48. `src/components/ui/carousel.tsx:185` - onClick -> `scrollPrev`
49. `src/components/ui/carousel.tsx:213` - onClick -> `scrollNext`
50. `src/components/ui/sidebar.tsx:155` - onOpenChange -> `setOpenMobile`
51. `src/components/ui/sidebar.tsx:254` - onClick -> `toggleSidebar`
52. `src/components/WebcamFeed.tsx:33` - onClick -> `restart`
53. `src/pages/CourseMap.tsx:214` - onClose -> `() => setPremiumOpen(false)`
54. `src/pages/Dashboard.tsx:104` - onClick -> `() => handleTabChange("courses")`
55. `src/pages/Dashboard.tsx:116` - onClick -> `() => handleTabChange(tab.id)`
56. `src/pages/Dashboard.tsx:154` - onClick -> `toggleTheme`
57. `src/pages/Dashboard.tsx:165` - onClick -> `() => handleTabChange("profile")`
58. `src/pages/Dashboard.tsx:179` - onClick -> `handleLogout`
59. `src/pages/Dashboard.tsx:197` - onClick -> `() => setMobileDrawerOpen(false)`
60. `src/pages/Dashboard.tsx:206` - onClick -> `() => setMobileDrawerOpen(false)`
61. `src/pages/Dashboard.tsx:217` - onClick -> `() => handleTabChange(tab.id)`
62. `src/pages/Dashboard.tsx:243` - onClick -> `toggleTheme`
63. `src/pages/Dashboard.tsx:248` - onClick -> `handleLogout`
64. `src/pages/Dashboard.tsx:262` - onClick -> `() => setSidebarOpen(!sidebarOpen)`
65. `src/pages/Dashboard.tsx:266` - onClick -> `() => setMobileDrawerOpen(true)`
66. `src/pages/Dashboard.tsx:277` - onClick -> `toggleTheme`
67. `src/pages/Dashboard.tsx:339` - onClick -> `() => handleTabChange(tabId)`
68. `src/pages/Dashboard.tsx:354` - onClose -> `() => setPremiumOpen(false)`
69. `src/pages/Dictionary.tsx:78` - onChange -> `e => setQuery(e.target.value)`
70. `src/pages/Dictionary.tsx:87` - onClick -> `() => setSelectedCategory(null)`
71. `src/pages/Dictionary.tsx:98` - onClick -> `() => setSelectedCategory(cat === selectedCategory ? null : cat)`
72. `src/pages/Dictionary.tsx:127` - onClick -> `() => entry.videoSrc && setActiveVideo(entry)`
73. `src/pages/Dictionary.tsx:158` - onClick -> `() => setActiveVideo(null)`
74. `src/pages/Dictionary.tsx:166` - onClick -> `e => e.stopPropagation()`
75. `src/pages/Dictionary.tsx:177` - onClick -> `() => setActiveVideo(null)`
76. `src/pages/Landing.tsx:25` - onClick -> `() => window.scrollTo({ top: 0, behavior: "smooth"`
77. `src/pages/Landing.tsx:27` - onClick -> `() => scrollTo("about")`
78. `src/pages/Landing.tsx:28` - onClick -> `() => scrollTo("features")`
79. `src/pages/Landing.tsx:29` - onClick -> `() => scrollTo("contact")`
80. `src/pages/Landing.tsx:33` - onClick -> `openLogin`
81. `src/pages/Landing.tsx:36` - onClick -> `openSignup`
82. `src/pages/Landing.tsx:58` - onClick -> `openSignup`
83. `src/pages/Landing.tsx:72` - onClick -> `() => scrollTo("features")`
84. `src/pages/Landing.tsx:138` - onClose -> `() => setLoginOpen(false)`
85. `src/pages/MockExam.tsx:93` - onClick -> `() => setStarted(true)`
86. `src/pages/MockExam.tsx:143` - onClick -> `handleReset`
87. `src/pages/MockExam.tsx:175` - onClick -> `() => setCurrentQ(i)`
88. `src/pages/MockExam.tsx:208` - onClick -> `() => handleAnswer(idx)`
89. `src/pages/MockExam.tsx:223` - onClick -> `() => setCurrentQ(Math.max(0, currentQ - 1))`
90. `src/pages/MockExam.tsx:232` - onClick -> `() => setCurrentQ(currentQ + 1)`
91. `src/pages/MockExam.tsx:239` - onClick -> `handleSubmit`
92. `src/pages/Onboarding.tsx:84` - onClick -> `() => navigate("/")`
93. `src/pages/Onboarding.tsx:143` - onClick -> `handleSkip`
94. `src/pages/Profile.tsx:75` - onClick -> `() => fileRef.current?.click()`
95. `src/pages/Profile.tsx:82` - onClick -> `() => fileRef.current?.click()`
96. `src/pages/Profile.tsx:87` - onChange -> `handleAvatarChange`
97. `src/pages/Profile.tsx:104` - onChange -> `e => setEditName(e.target.value)`
98. `src/pages/Profile.tsx:109` - onChange -> `e => setEditBio(e.target.value)`
99. `src/pages/Profile.tsx:125` - onClick -> `handleCancel`
100. `src/pages/Profile.tsx:128` - onClick -> `handleSave`
101. `src/pages/Profile.tsx:134` - onClick -> `() => setEditing(true)`
102. `src/pages/Profile.tsx:195` - onClick -> `() => setReminderEnabled(!reminderEnabled)`
103. `src/pages/Profile.tsx:207` - onChange -> `e => setReminderTime(e.target.value)`
104. `src/pages/ReviewChallenge.tsx:101` - onClick -> `handleReset`
105. `src/pages/ReviewChallenge.tsx:126` - onClick -> `() => setFlipped(!flipped)`
106. `src/pages/ReviewChallenge.tsx:147` - onClick -> `() => handleNext(false)`
107. `src/pages/ReviewChallenge.tsx:151` - onClick -> `() => handleNext(true)`
108. `src/pages/ReviewChallenge.tsx:224` - onClick -> `handleReset`
109. `src/pages/ReviewChallenge.tsx:260` - onClick -> `() => handleSelect(i)`
110. `src/pages/ReviewChallenge.tsx:336` - onClick -> `handleReset`
111. `src/pages/ReviewChallenge.tsx:358` - onClick -> `() => handleSelect(item)`
112. `src/pages/ReviewChallenge.tsx:400` - onClick -> `() => setActiveMode(null)`
113. `src/pages/ReviewChallenge.tsx:458` - onClick -> `() => setActiveMode(mode.id)`
114. `src/pages/VocabularyPack.tsx:207` - onClick -> `() => handleSelect(idx)`
115. `src/pages/VocabularyPack.tsx:240` - onChange -> `e => setAnswer(e.target.value)`
116. `src/pages/VocabularyPack.tsx:241` - onKeyDown -> `e => e.key === "Enter" && handleSubmit()`
117. `src/pages/VocabularyPack.tsx:244` - onClick -> `handleSubmit`
118. `src/pages/VocabularyPack.tsx:279` - onClick -> `handleScan`
119. `src/pages/VocabularyPack.tsx:303` - onClick -> `onBack`
120. `src/pages/VocabularyPack.tsx:333` - onClick -> `() => setShowVideo(true)`
121. `src/pages/VocabularyPack.tsx:343` - onClick -> `() => lesson.quiz ? setPhase("quiz") : onComplete()`
122. `src/pages/VocabularyPack.tsx:353` - onClose -> `() => setShowVideo(false)`
123. `src/pages/VocabularyPack.tsx:354` - onComplete -> `() => setShowVideo(false)`
124. `src/pages/VocabularyPack.tsx:363` - onClick -> `() => setShowVideo(!showVideo)`
125. `src/pages/VocabularyPack.tsx:373` - onComplete -> `handleQuizComplete`
126. `src/pages/VocabularyPack.tsx:374` - onComplete -> `handleQuizComplete`
127. `src/pages/VocabularyPack.tsx:375` - onComplete -> `handleQuizComplete`
128. `src/pages/VocabularyPack.tsx:424` - onClose -> `() => setVideoLessonId(null)`
129. `src/pages/VocabularyPack.tsx:446` - onBack -> `() => setActiveLessonIdx(null)`
130. `src/pages/VocabularyPack.tsx:447` - onComplete -> `() => handleLessonComplete(lesson.id, activeLessonIdx)`
131. `src/pages/VocabularyPack.tsx:457` - onClick -> `onBack`
132. `src/pages/VocabularyPack.tsx:535` - onClick -> `() => startLesson(chapter.lessons[firstIncomplete], firstIncomplete)`
133. `src/pages/VocabularyPack.tsx:544` - onClose -> `() => setPremiumOpen(false)`
134. `src/pages/VocabularyPack.tsx:571` - onClick -> `() => setPremiumOpen(true)`
135. `src/pages/VocabularyPack.tsx:575` - onClick -> `onGoNext`
136. `src/pages/VocabularyPack.tsx:580` - onClick -> `onBackToUnit`
137. `src/pages/VocabularyPack.tsx:585` - onClose -> `() => setPremiumOpen(false)`
138. `src/pages/VocabularyPack.tsx:611` - onGoNext -> `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(completedChapterIdx + 1);`
139. `src/pages/VocabularyPack.tsx:612` - onBackToUnit -> `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(null);`
140. `src/pages/VocabularyPack.tsx:624` - onBack -> `() => setSelectedChapterIdx(null)`
141. `src/pages/VocabularyPack.tsx:625` - onChapterComplete -> `() => setCompletedChapterIdx(selectedChapterIdx)`
142. `src/pages/VocabularyPack.tsx:637` - onClick -> `onBack`
143. `src/pages/VocabularyPack.tsx:708` - onClick -> `e => { e.stopPropagation(); setSelectedChapterIdx(idx);`
144. `src/pages/VocabularyPack.tsx:724` - onClose -> `() => setPremiumOpen(false)`
145. `src/pages/VocabularyPack.tsx:753` - onBack -> `() => setState({ view: "units"`
146. `src/pages/VocabularyPack.tsx:783` - onClick -> `() => setState({ view: "unit", unitIdx: i`

## Flow 1: onClick in src/components/LessonModal.tsx:159

**Entry expression:** `() => handleSelect(idx)`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:159` with expression `() => handleSelect(idx)`.
2. Inline handler expression executes.
3. Call local function `handleSelect()`
4. Inside `handleSelect()` (src/components/LessonModal.tsx:139).
5. Update state `selected` via `setSelected(...)`
6. Update state `showResult` via `setShowResult(...)`

## Flow 2: onClick in src/components/LessonModal.tsx:196

**Entry expression:** `onCorrect`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:196` with expression `onCorrect`.
2. Handler `onCorrect` is referenced but not resolved in this file.

## Flow 3: onStatusChange in src/components/LessonModal.tsx:241

**Entry expression:** `handleCamStatus`

### Chronological Steps

1. User triggers `onStatusChange` on `src/components/LessonModal.tsx:241` with expression `handleCamStatus`.
2. Handler `handleCamStatus` is referenced but not resolved in this file.

## Flow 4: onClick in src/components/LessonModal.tsx:255

**Entry expression:** `handleScan`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:255` with expression `handleScan`.
2. Execute handler `handleScan()` (src/components/LessonModal.tsx:224).
3. Update state `scanning` via `setScanning(...)`
4. Update state `scanning` via `setScanning(...)`
5. Update state `scanDone` via `setScanDone(...)`

## Flow 5: onClick in src/components/LessonModal.tsx:279

**Entry expression:** `onNextLesson`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:279` with expression `onNextLesson`.
2. Handler `onNextLesson` is referenced but not resolved in this file.

## Flow 6: onClick in src/components/LessonModal.tsx:283

**Entry expression:** `onFinish`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:283` with expression `onFinish`.
2. Handler `onFinish` is referenced but not resolved in this file.

## Flow 7: onClick in src/components/LessonModal.tsx:332

**Entry expression:** `onClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:332` with expression `onClose`.
2. Handler `onClose` is referenced but not resolved in this file.

## Flow 8: onClick in src/components/LessonModal.tsx:383

**Entry expression:** `() => setStep(2)`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonModal.tsx:383` with expression `() => setStep(2)`.
2. Inline handler expression executes.
3. Update state `step` via `setStep(...)`

## Flow 9: onCorrect in src/components/LessonModal.tsx:410

**Entry expression:** `handlePracticeComplete`

### Chronological Steps

1. User triggers `onCorrect` on `src/components/LessonModal.tsx:410` with expression `handlePracticeComplete`.
2. Execute handler `handlePracticeComplete()` (src/components/LessonModal.tsx:311).
3. Update state `step` via `setStep(...)`

## Flow 10: onSuccess in src/components/LessonModal.tsx:415

**Entry expression:** `handlePracticeComplete`

### Chronological Steps

1. User triggers `onSuccess` on `src/components/LessonModal.tsx:415` with expression `handlePracticeComplete`.
2. Execute handler `handlePracticeComplete()` (src/components/LessonModal.tsx:311).
3. Update state `step` via `setStep(...)`

## Flow 11: onFinish in src/components/LessonModal.tsx:429

**Entry expression:** `handleFinish`

### Chronological Steps

1. User triggers `onFinish` on `src/components/LessonModal.tsx:429` with expression `handleFinish`.
2. Execute handler `handleFinish()` (src/components/LessonModal.tsx:315).
3. No state/API/routing/local-call side effects detected in `handleFinish()`.

## Flow 12: onNextLesson in src/components/LessonModal.tsx:429

**Entry expression:** `onNextLesson`

### Chronological Steps

1. User triggers `onNextLesson` on `src/components/LessonModal.tsx:429` with expression `onNextLesson`.
2. Handler `onNextLesson` is referenced but not resolved in this file.

## Flow 13: onClick in src/components/LessonView.tsx:66

**Entry expression:** `() => handleSelect(idx)`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:66` with expression `() => handleSelect(idx)`.
2. Inline handler expression executes.
3. Call local function `handleSelect()`
4. Inside `handleSelect()` (src/components/LessonView.tsx:48).
5. Update state `selected` via `setSelected(...)`
6. Update state `showResult` via `setShowResult(...)`

## Flow 14: onChange in src/components/LessonView.tsx:118

**Entry expression:** `e => setAnswer(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/LessonView.tsx:118` with expression `e => setAnswer(e.target.value)`.
2. Inline handler expression executes.
3. Update state `answer` via `setAnswer(...)`

## Flow 15: onKeyDown in src/components/LessonView.tsx:119

**Entry expression:** `e => e.key === "Enter" && handleSubmit()`

### Chronological Steps

1. User triggers `onKeyDown` on `src/components/LessonView.tsx:119` with expression `e => e.key === "Enter" && handleSubmit()`.
2. Inline handler expression executes.
3. Call local function `handleSubmit()`
4. Inside `handleSubmit()` (src/components/LessonView.tsx:101).
5. Update state `showResult` via `setShowResult(...)`

## Flow 16: onClick in src/components/LessonView.tsx:123

**Entry expression:** `handleSubmit`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:123` with expression `handleSubmit`.
2. Execute handler `handleSubmit()` (src/components/LessonView.tsx:101).
3. Update state `showResult` via `setShowResult(...)`

## Flow 17: onClick in src/components/LessonView.tsx:197

**Entry expression:** `startCamera`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:197` with expression `startCamera`.
2. Execute handler `startCamera()` (src/components/LessonView.tsx:148).
3. Update state `cameraOn` via `setCameraOn(...)`

## Flow 18: onClick in src/components/LessonView.tsx:201

**Entry expression:** `handleRecord`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:201` with expression `handleRecord`.
2. Execute handler `handleRecord()` (src/components/LessonView.tsx:160).
3. Update state `recording` via `setRecording(...)`
4. Update state `recording` via `setRecording(...)`

## Flow 19: onClick in src/components/LessonView.tsx:293

**Entry expression:** `() => setSpeedMenuOpen(!speedMenuOpen)`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:293` with expression `() => setSpeedMenuOpen(!speedMenuOpen)`.
2. Inline handler expression executes.
3. Update state `speedMenuOpen` via `setSpeedMenuOpen(...)`

## Flow 20: onClick in src/components/LessonView.tsx:311

**Entry expression:** `() => { setPlaybackSpeed(speed); setSpeedMenuOpen(false);`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:311` with expression `() => { setPlaybackSpeed(speed); setSpeedMenuOpen(false);`.
2. Inline handler expression executes.
3. Update state `playbackSpeed` via `setPlaybackSpeed(...)`
4. Update state `speedMenuOpen` via `setSpeedMenuOpen(...)`

## Flow 21: onComplete in src/components/LessonView.tsx:333

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/components/LessonView.tsx:333` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/components/LessonView.tsx:231).
3. Update state `current` via `setCurrent(...)`
4. Update state `phase` via `setPhase(...)`

## Flow 22: onComplete in src/components/LessonView.tsx:336

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/components/LessonView.tsx:336` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/components/LessonView.tsx:231).
3. Update state `current` via `setCurrent(...)`
4. Update state `phase` via `setPhase(...)`

## Flow 23: onComplete in src/components/LessonView.tsx:339

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/components/LessonView.tsx:339` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/components/LessonView.tsx:231).
3. Update state `current` via `setCurrent(...)`
4. Update state `phase` via `setPhase(...)`

## Flow 24: onClick in src/components/LessonView.tsx:363

**Entry expression:** `handleNext`

### Chronological Steps

1. User triggers `onClick` on `src/components/LessonView.tsx:363` with expression `handleNext`.
2. Execute handler `handleNext()` (src/components/LessonView.tsx:219).
3. Update state `phase` via `setPhase(...)`
4. Update state `current` via `setCurrent(...)`
5. Update state `phase` via `setPhase(...)`

## Flow 25: onClick in src/components/LoginModal.tsx:38

**Entry expression:** `onClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/LoginModal.tsx:38` with expression `onClose`.
2. Handler `onClose` is referenced but not resolved in this file.

## Flow 26: onClick in src/components/LoginModal.tsx:45

**Entry expression:** `e => e.stopPropagation()`

### Chronological Steps

1. User triggers `onClick` on `src/components/LoginModal.tsx:45` with expression `e => e.stopPropagation()`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 27: onClick in src/components/LoginModal.tsx:51

**Entry expression:** `onClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/LoginModal.tsx:51` with expression `onClose`.
2. Handler `onClose` is referenced but not resolved in this file.

## Flow 28: onSubmit in src/components/LoginModal.tsx:56

**Entry expression:** `handleSubmit`

### Chronological Steps

1. User triggers `onSubmit` on `src/components/LoginModal.tsx:56` with expression `handleSubmit`.
2. Execute handler `handleSubmit()` (src/components/LoginModal.tsx:22).
3. Update state `name` via `setName(...)`
4. Update state `email` via `setEmail(...)`

## Flow 29: onChange in src/components/LoginModal.tsx:61

**Entry expression:** `e => setName(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/LoginModal.tsx:61` with expression `e => setName(e.target.value)`.
2. Inline handler expression executes.
3. Update state `name` via `setName(...)`

## Flow 30: onChange in src/components/LoginModal.tsx:70

**Entry expression:** `e => setEmail(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/LoginModal.tsx:70` with expression `e => setEmail(e.target.value)`.
2. Inline handler expression executes.
3. Update state `email` via `setEmail(...)`

## Flow 31: onClick in src/components/LoginModal.tsx:91

**Entry expression:** `() => setIsSignUp(!isSignUp)`

### Chronological Steps

1. User triggers `onClick` on `src/components/LoginModal.tsx:91` with expression `() => setIsSignUp(!isSignUp)`.
2. Inline handler expression executes.
3. Update state `isSignUp` via `setIsSignUp(...)`

## Flow 32: onClick in src/components/PracticeView.tsx:95

**Entry expression:** `checkSign`

### Chronological Steps

1. User triggers `onClick` on `src/components/PracticeView.tsx:95` with expression `checkSign`.
2. Execute handler `checkSign()` (src/components/PracticeView.tsx:12).
3. Update state `scanning` via `setScanning(...)`
4. Update state `result` via `setResult(...)`
5. Update state `scanning` via `setScanning(...)`
6. Update state `result` via `setResult(...)`

## Flow 33: onClick in src/components/PremiumModal.tsx:75

**Entry expression:** `handleClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:75` with expression `handleClose`.
2. Execute handler `handleClose()` (src/components/PremiumModal.tsx:47).
3. Update state `method` via `setMethod(...)`
4. Update state `loading` via `setLoading(...)`
5. Update state `success` via `setSuccess(...)`
6. Update state `qrCountdown` via `setQrCountdown(...)`
7. Update state `cardNumber` via `setCardNumber(...)`
8. Update state `cardName` via `setCardName(...)`
9. Update state `cardExpiry` via `setCardExpiry(...)`
10. Update state `cardCvv` via `setCardCvv(...)`

## Flow 34: onClick in src/components/PremiumModal.tsx:82

**Entry expression:** `e => e.stopPropagation()`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:82` with expression `e => e.stopPropagation()`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 35: onClick in src/components/PremiumModal.tsx:100

**Entry expression:** `handleClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:100` with expression `handleClose`.
2. Execute handler `handleClose()` (src/components/PremiumModal.tsx:47).
3. Update state `method` via `setMethod(...)`
4. Update state `loading` via `setLoading(...)`
5. Update state `success` via `setSuccess(...)`
6. Update state `qrCountdown` via `setQrCountdown(...)`
7. Update state `cardNumber` via `setCardNumber(...)`
8. Update state `cardName` via `setCardName(...)`
9. Update state `cardExpiry` via `setCardExpiry(...)`
10. Update state `cardCvv` via `setCardCvv(...)`

## Flow 36: onClick in src/components/PremiumModal.tsx:108

**Entry expression:** `handleClose`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:108` with expression `handleClose`.
2. Execute handler `handleClose()` (src/components/PremiumModal.tsx:47).
3. Update state `method` via `setMethod(...)`
4. Update state `loading` via `setLoading(...)`
5. Update state `success` via `setSuccess(...)`
6. Update state `qrCountdown` via `setQrCountdown(...)`
7. Update state `cardNumber` via `setCardNumber(...)`
8. Update state `cardName` via `setCardName(...)`
9. Update state `cardExpiry` via `setCardExpiry(...)`
10. Update state `cardCvv` via `setCardCvv(...)`

## Flow 37: onClick in src/components/PremiumModal.tsx:134

**Entry expression:** `() => setMethod("ewallet")`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:134` with expression `() => setMethod("ewallet")`.
2. Inline handler expression executes.
3. Update state `method` via `setMethod(...)`

## Flow 38: onClick in src/components/PremiumModal.tsx:151

**Entry expression:** `() => setMethod("card")`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:151` with expression `() => setMethod("card")`.
2. Inline handler expression executes.
3. Update state `method` via `setMethod(...)`

## Flow 39: onChange in src/components/PremiumModal.tsx:174

**Entry expression:** `e => setCardNumber(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/PremiumModal.tsx:174` with expression `e => setCardNumber(e.target.value)`.
2. Inline handler expression executes.
3. Update state `cardNumber` via `setCardNumber(...)`

## Flow 40: onChange in src/components/PremiumModal.tsx:177

**Entry expression:** `e => setCardName(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/PremiumModal.tsx:177` with expression `e => setCardName(e.target.value)`.
2. Inline handler expression executes.
3. Update state `cardName` via `setCardName(...)`

## Flow 41: onChange in src/components/PremiumModal.tsx:181

**Entry expression:** `e => setCardExpiry(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/PremiumModal.tsx:181` with expression `e => setCardExpiry(e.target.value)`.
2. Inline handler expression executes.
3. Update state `cardExpiry` via `setCardExpiry(...)`

## Flow 42: onChange in src/components/PremiumModal.tsx:184

**Entry expression:** `e => setCardCvv(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/components/PremiumModal.tsx:184` with expression `e => setCardCvv(e.target.value)`.
2. Inline handler expression executes.
3. Update state `cardCvv` via `setCardCvv(...)`

## Flow 43: onClick in src/components/PremiumModal.tsx:195

**Entry expression:** `() => setMethod("qr")`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:195` with expression `() => setMethod("qr")`.
2. Inline handler expression executes.
3. Update state `method` via `setMethod(...)`

## Flow 44: onClick in src/components/PremiumModal.tsx:237

**Entry expression:** `handlePay`

### Chronological Steps

1. User triggers `onClick` on `src/components/PremiumModal.tsx:237` with expression `handlePay`.
2. Execute handler `handlePay()` (src/components/PremiumModal.tsx:38).
3. Update state `loading` via `setLoading(...)`
4. Update state `loading` via `setLoading(...)`
5. Update state `success` via `setSuccess(...)`

## Flow 45: onClick in src/components/QuizView.tsx:61

**Entry expression:** `() => { setQIdx(0); setSelected(null); setScore(0); setFinished(false);`

### Chronological Steps

1. User triggers `onClick` on `src/components/QuizView.tsx:61` with expression `() => { setQIdx(0); setSelected(null); setScore(0); setFinished(false);`.
2. Inline handler expression executes.
3. Update state `qIdx` via `setQIdx(...)`
4. Update state `selected` via `setSelected(...)`
5. Update state `score` via `setScore(...)`
6. Update state `finished` via `setFinished(...)`

## Flow 46: onClick in src/components/QuizView.tsx:119

**Entry expression:** `() => handleAnswer(idx)`

### Chronological Steps

1. User triggers `onClick` on `src/components/QuizView.tsx:119` with expression `() => handleAnswer(idx)`.
2. Inline handler expression executes.
3. Call local function `handleAnswer()`
4. Inside `handleAnswer()` (src/components/QuizView.tsx:30).
5. Update state `selected` via `setSelected(...)`
6. Update state `score` via `setScore(...)`
7. Update state `qIdx` via `setQIdx(...)`
8. Update state `selected` via `setSelected(...)`
9. Update state `finished` via `setFinished(...)`

## Flow 47: onKeyDownCapture in src/components/ui/carousel.tsx:120

**Entry expression:** `handleKeyDown`

### Chronological Steps

1. User triggers `onKeyDownCapture` on `src/components/ui/carousel.tsx:120` with expression `handleKeyDown`.
2. Handler `handleKeyDown` is referenced but not resolved in this file.

## Flow 48: onClick in src/components/ui/carousel.tsx:185

**Entry expression:** `scrollPrev`

### Chronological Steps

1. User triggers `onClick` on `src/components/ui/carousel.tsx:185` with expression `scrollPrev`.
2. Handler `scrollPrev` is referenced but not resolved in this file.

## Flow 49: onClick in src/components/ui/carousel.tsx:213

**Entry expression:** `scrollNext`

### Chronological Steps

1. User triggers `onClick` on `src/components/ui/carousel.tsx:213` with expression `scrollNext`.
2. Handler `scrollNext` is referenced but not resolved in this file.

## Flow 50: onOpenChange in src/components/ui/sidebar.tsx:155

**Entry expression:** `setOpenMobile`

### Chronological Steps

1. User triggers `onOpenChange` on `src/components/ui/sidebar.tsx:155` with expression `setOpenMobile`.
2. Handler `setOpenMobile` is referenced but not resolved in this file.

## Flow 51: onClick in src/components/ui/sidebar.tsx:254

**Entry expression:** `toggleSidebar`

### Chronological Steps

1. User triggers `onClick` on `src/components/ui/sidebar.tsx:254` with expression `toggleSidebar`.
2. Handler `toggleSidebar` is referenced but not resolved in this file.

## Flow 52: onClick in src/components/WebcamFeed.tsx:33

**Entry expression:** `restart`

### Chronological Steps

1. User triggers `onClick` on `src/components/WebcamFeed.tsx:33` with expression `restart`.
2. Handler `restart` is referenced but not resolved in this file.

## Flow 53: onClose in src/pages/CourseMap.tsx:214

**Entry expression:** `() => setPremiumOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/CourseMap.tsx:214` with expression `() => setPremiumOpen(false)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 54: onClick in src/pages/Dashboard.tsx:104

**Entry expression:** `() => handleTabChange("courses")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:104` with expression `() => handleTabChange("courses")`.
2. Inline handler expression executes.
3. Call local function `handleTabChange()`
4. Inside `handleTabChange()` (src/pages/Dashboard.tsx:74).
5. Update state `premiumOpen` via `setPremiumOpen(...)`
6. Update state `activeTab` via `setActiveTab(...)`
7. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 55: onClick in src/pages/Dashboard.tsx:116

**Entry expression:** `() => handleTabChange(tab.id)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:116` with expression `() => handleTabChange(tab.id)`.
2. Inline handler expression executes.
3. Call local function `handleTabChange()`
4. Inside `handleTabChange()` (src/pages/Dashboard.tsx:74).
5. Update state `premiumOpen` via `setPremiumOpen(...)`
6. Update state `activeTab` via `setActiveTab(...)`
7. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 56: onClick in src/pages/Dashboard.tsx:154

**Entry expression:** `toggleTheme`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:154` with expression `toggleTheme`.
2. Handler `toggleTheme` is referenced but not resolved in this file.

## Flow 57: onClick in src/pages/Dashboard.tsx:165

**Entry expression:** `() => handleTabChange("profile")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:165` with expression `() => handleTabChange("profile")`.
2. Inline handler expression executes.
3. Call local function `handleTabChange()`
4. Inside `handleTabChange()` (src/pages/Dashboard.tsx:74).
5. Update state `premiumOpen` via `setPremiumOpen(...)`
6. Update state `activeTab` via `setActiveTab(...)`
7. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 58: onClick in src/pages/Dashboard.tsx:179

**Entry expression:** `handleLogout`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:179` with expression `handleLogout`.
2. Execute handler `handleLogout()` (src/pages/Dashboard.tsx:72).
3. Route change through `navigate(...)` with "/"

## Flow 59: onClick in src/pages/Dashboard.tsx:197

**Entry expression:** `() => setMobileDrawerOpen(false)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:197` with expression `() => setMobileDrawerOpen(false)`.
2. Inline handler expression executes.
3. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 60: onClick in src/pages/Dashboard.tsx:206

**Entry expression:** `() => setMobileDrawerOpen(false)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:206` with expression `() => setMobileDrawerOpen(false)`.
2. Inline handler expression executes.
3. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 61: onClick in src/pages/Dashboard.tsx:217

**Entry expression:** `() => handleTabChange(tab.id)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:217` with expression `() => handleTabChange(tab.id)`.
2. Inline handler expression executes.
3. Call local function `handleTabChange()`
4. Inside `handleTabChange()` (src/pages/Dashboard.tsx:74).
5. Update state `premiumOpen` via `setPremiumOpen(...)`
6. Update state `activeTab` via `setActiveTab(...)`
7. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 62: onClick in src/pages/Dashboard.tsx:243

**Entry expression:** `toggleTheme`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:243` with expression `toggleTheme`.
2. Handler `toggleTheme` is referenced but not resolved in this file.

## Flow 63: onClick in src/pages/Dashboard.tsx:248

**Entry expression:** `handleLogout`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:248` with expression `handleLogout`.
2. Execute handler `handleLogout()` (src/pages/Dashboard.tsx:72).
3. Route change through `navigate(...)` with "/"

## Flow 64: onClick in src/pages/Dashboard.tsx:262

**Entry expression:** `() => setSidebarOpen(!sidebarOpen)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:262` with expression `() => setSidebarOpen(!sidebarOpen)`.
2. Inline handler expression executes.
3. Update state `sidebarOpen` via `setSidebarOpen(...)`

## Flow 65: onClick in src/pages/Dashboard.tsx:266

**Entry expression:** `() => setMobileDrawerOpen(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:266` with expression `() => setMobileDrawerOpen(true)`.
2. Inline handler expression executes.
3. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 66: onClick in src/pages/Dashboard.tsx:277

**Entry expression:** `toggleTheme`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:277` with expression `toggleTheme`.
2. Handler `toggleTheme` is referenced but not resolved in this file.

## Flow 67: onClick in src/pages/Dashboard.tsx:339

**Entry expression:** `() => handleTabChange(tabId)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dashboard.tsx:339` with expression `() => handleTabChange(tabId)`.
2. Inline handler expression executes.
3. Call local function `handleTabChange()`
4. Inside `handleTabChange()` (src/pages/Dashboard.tsx:74).
5. Update state `premiumOpen` via `setPremiumOpen(...)`
6. Update state `activeTab` via `setActiveTab(...)`
7. Update state `mobileDrawerOpen` via `setMobileDrawerOpen(...)`

## Flow 68: onClose in src/pages/Dashboard.tsx:354

**Entry expression:** `() => setPremiumOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/Dashboard.tsx:354` with expression `() => setPremiumOpen(false)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 69: onChange in src/pages/Dictionary.tsx:78

**Entry expression:** `e => setQuery(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/pages/Dictionary.tsx:78` with expression `e => setQuery(e.target.value)`.
2. Inline handler expression executes.
3. Update state `query` via `setQuery(...)`

## Flow 70: onClick in src/pages/Dictionary.tsx:87

**Entry expression:** `() => setSelectedCategory(null)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:87` with expression `() => setSelectedCategory(null)`.
2. Inline handler expression executes.
3. Update state `selectedCategory` via `setSelectedCategory(...)`

## Flow 71: onClick in src/pages/Dictionary.tsx:98

**Entry expression:** `() => setSelectedCategory(cat === selectedCategory ? null : cat)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:98` with expression `() => setSelectedCategory(cat === selectedCategory ? null : cat)`.
2. Inline handler expression executes.
3. Update state `selectedCategory` via `setSelectedCategory(...)`

## Flow 72: onClick in src/pages/Dictionary.tsx:127

**Entry expression:** `() => entry.videoSrc && setActiveVideo(entry)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:127` with expression `() => entry.videoSrc && setActiveVideo(entry)`.
2. Inline handler expression executes.
3. Update state `activeVideo` via `setActiveVideo(...)`

## Flow 73: onClick in src/pages/Dictionary.tsx:158

**Entry expression:** `() => setActiveVideo(null)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:158` with expression `() => setActiveVideo(null)`.
2. Inline handler expression executes.
3. Update state `activeVideo` via `setActiveVideo(...)`

## Flow 74: onClick in src/pages/Dictionary.tsx:166

**Entry expression:** `e => e.stopPropagation()`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:166` with expression `e => e.stopPropagation()`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 75: onClick in src/pages/Dictionary.tsx:177

**Entry expression:** `() => setActiveVideo(null)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Dictionary.tsx:177` with expression `() => setActiveVideo(null)`.
2. Inline handler expression executes.
3. Update state `activeVideo` via `setActiveVideo(...)`

## Flow 76: onClick in src/pages/Landing.tsx:25

**Entry expression:** `() => window.scrollTo({ top: 0, behavior: "smooth"`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:25` with expression `() => window.scrollTo({ top: 0, behavior: "smooth"`.
2. Inline handler expression executes.
3. Call local function `scrollTo()`
4. Inside `scrollTo()` (src/pages/Landing.tsx:12).
5. No state/API/routing/local-call side effects detected in `scrollTo()`.

## Flow 77: onClick in src/pages/Landing.tsx:27

**Entry expression:** `() => scrollTo("about")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:27` with expression `() => scrollTo("about")`.
2. Inline handler expression executes.
3. Call local function `scrollTo()`
4. Inside `scrollTo()` (src/pages/Landing.tsx:12).
5. No state/API/routing/local-call side effects detected in `scrollTo()`.

## Flow 78: onClick in src/pages/Landing.tsx:28

**Entry expression:** `() => scrollTo("features")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:28` with expression `() => scrollTo("features")`.
2. Inline handler expression executes.
3. Call local function `scrollTo()`
4. Inside `scrollTo()` (src/pages/Landing.tsx:12).
5. No state/API/routing/local-call side effects detected in `scrollTo()`.

## Flow 79: onClick in src/pages/Landing.tsx:29

**Entry expression:** `() => scrollTo("contact")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:29` with expression `() => scrollTo("contact")`.
2. Inline handler expression executes.
3. Call local function `scrollTo()`
4. Inside `scrollTo()` (src/pages/Landing.tsx:12).
5. No state/API/routing/local-call side effects detected in `scrollTo()`.

## Flow 80: onClick in src/pages/Landing.tsx:33

**Entry expression:** `openLogin`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:33` with expression `openLogin`.
2. Execute handler `openLogin()` (src/pages/Landing.tsx:16).
3. Update state `loginMode` via `setLoginMode(...)`
4. Update state `loginOpen` via `setLoginOpen(...)`

## Flow 81: onClick in src/pages/Landing.tsx:36

**Entry expression:** `openSignup`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:36` with expression `openSignup`.
2. Execute handler `openSignup()` (src/pages/Landing.tsx:17).
3. Update state `loginMode` via `setLoginMode(...)`
4. Update state `loginOpen` via `setLoginOpen(...)`

## Flow 82: onClick in src/pages/Landing.tsx:58

**Entry expression:** `openSignup`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:58` with expression `openSignup`.
2. Execute handler `openSignup()` (src/pages/Landing.tsx:17).
3. Update state `loginMode` via `setLoginMode(...)`
4. Update state `loginOpen` via `setLoginOpen(...)`

## Flow 83: onClick in src/pages/Landing.tsx:72

**Entry expression:** `() => scrollTo("features")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Landing.tsx:72` with expression `() => scrollTo("features")`.
2. Inline handler expression executes.
3. Call local function `scrollTo()`
4. Inside `scrollTo()` (src/pages/Landing.tsx:12).
5. No state/API/routing/local-call side effects detected in `scrollTo()`.

## Flow 84: onClose in src/pages/Landing.tsx:138

**Entry expression:** `() => setLoginOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/Landing.tsx:138` with expression `() => setLoginOpen(false)`.
2. Inline handler expression executes.
3. Update state `loginOpen` via `setLoginOpen(...)`

## Flow 85: onClick in src/pages/MockExam.tsx:93

**Entry expression:** `() => setStarted(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:93` with expression `() => setStarted(true)`.
2. Inline handler expression executes.
3. Update state `started` via `setStarted(...)`

## Flow 86: onClick in src/pages/MockExam.tsx:143

**Entry expression:** `handleReset`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:143` with expression `handleReset`.
2. Execute handler `handleReset()` (src/pages/MockExam.tsx:67).
3. Update state `started` via `setStarted(...)`
4. Update state `timeLeft` via `setTimeLeft(...)`
5. Update state `currentQ` via `setCurrentQ(...)`
6. Update state `answers` via `setAnswers(...)`
7. Update state `submitted` via `setSubmitted(...)`

## Flow 87: onClick in src/pages/MockExam.tsx:175

**Entry expression:** `() => setCurrentQ(i)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:175` with expression `() => setCurrentQ(i)`.
2. Inline handler expression executes.
3. Update state `currentQ` via `setCurrentQ(...)`

## Flow 88: onClick in src/pages/MockExam.tsx:208

**Entry expression:** `() => handleAnswer(idx)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:208` with expression `() => handleAnswer(idx)`.
2. Inline handler expression executes.
3. Call local function `handleAnswer()`
4. Inside `handleAnswer()` (src/pages/MockExam.tsx:55).
5. Update state `answers` via `setAnswers(...)`

## Flow 89: onClick in src/pages/MockExam.tsx:223

**Entry expression:** `() => setCurrentQ(Math.max(0, currentQ - 1))`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:223` with expression `() => setCurrentQ(Math.max(0, currentQ - 1))`.
2. Inline handler expression executes.
3. Update state `currentQ` via `setCurrentQ(...)`

## Flow 90: onClick in src/pages/MockExam.tsx:232

**Entry expression:** `() => setCurrentQ(currentQ + 1)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:232` with expression `() => setCurrentQ(currentQ + 1)`.
2. Inline handler expression executes.
3. Update state `currentQ` via `setCurrentQ(...)`

## Flow 91: onClick in src/pages/MockExam.tsx:239

**Entry expression:** `handleSubmit`

### Chronological Steps

1. User triggers `onClick` on `src/pages/MockExam.tsx:239` with expression `handleSubmit`.
2. Handler `handleSubmit` is referenced but not resolved in this file.

## Flow 92: onClick in src/pages/Onboarding.tsx:84

**Entry expression:** `() => navigate("/")`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Onboarding.tsx:84` with expression `() => navigate("/")`.
2. Inline handler expression executes.
3. Route change through `navigate(...)` with "/"

## Flow 93: onClick in src/pages/Onboarding.tsx:143

**Entry expression:** `handleSkip`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Onboarding.tsx:143` with expression `handleSkip`.
2. Execute handler `handleSkip()` (src/pages/Onboarding.tsx:67).
3. Route change through `navigate(...)` with "/dashboard"

## Flow 94: onClick in src/pages/Profile.tsx:75

**Entry expression:** `() => fileRef.current?.click()`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:75` with expression `() => fileRef.current?.click()`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 95: onClick in src/pages/Profile.tsx:82

**Entry expression:** `() => fileRef.current?.click()`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:82` with expression `() => fileRef.current?.click()`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 96: onChange in src/pages/Profile.tsx:87

**Entry expression:** `handleAvatarChange`

### Chronological Steps

1. User triggers `onChange` on `src/pages/Profile.tsx:87` with expression `handleAvatarChange`.
2. Execute handler `handleAvatarChange()` (src/pages/Profile.tsx:40).
3. Update state `previewAvatar` via `setPreviewAvatar(...)`

## Flow 97: onChange in src/pages/Profile.tsx:104

**Entry expression:** `e => setEditName(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/pages/Profile.tsx:104` with expression `e => setEditName(e.target.value)`.
2. Inline handler expression executes.
3. Update state `editName` via `setEditName(...)`

## Flow 98: onChange in src/pages/Profile.tsx:109

**Entry expression:** `e => setEditBio(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/pages/Profile.tsx:109` with expression `e => setEditBio(e.target.value)`.
2. Inline handler expression executes.
3. Update state `editBio` via `setEditBio(...)`

## Flow 99: onClick in src/pages/Profile.tsx:125

**Entry expression:** `handleCancel`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:125` with expression `handleCancel`.
2. Execute handler `handleCancel()` (src/pages/Profile.tsx:53).
3. Update state `editName` via `setEditName(...)`
4. Update state `editBio` via `setEditBio(...)`
5. Update state `previewAvatar` via `setPreviewAvatar(...)`
6. Update state `editing` via `setEditing(...)`

## Flow 100: onClick in src/pages/Profile.tsx:128

**Entry expression:** `handleSave`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:128` with expression `handleSave`.
2. Execute handler `handleSave()` (src/pages/Profile.tsx:48).
3. Update state `editing` via `setEditing(...)`

## Flow 101: onClick in src/pages/Profile.tsx:134

**Entry expression:** `() => setEditing(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:134` with expression `() => setEditing(true)`.
2. Inline handler expression executes.
3. Update state `editing` via `setEditing(...)`

## Flow 102: onClick in src/pages/Profile.tsx:195

**Entry expression:** `() => setReminderEnabled(!reminderEnabled)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/Profile.tsx:195` with expression `() => setReminderEnabled(!reminderEnabled)`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 103: onChange in src/pages/Profile.tsx:207

**Entry expression:** `e => setReminderTime(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/pages/Profile.tsx:207` with expression `e => setReminderTime(e.target.value)`.
2. Inline handler expression executes.
3. No state/API/routing side effects detected in inline expression.

## Flow 104: onClick in src/pages/ReviewChallenge.tsx:101

**Entry expression:** `handleReset`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:101` with expression `handleReset`.
2. Execute handler `handleReset()` (src/pages/ReviewChallenge.tsx:77).
3. Update state `idx` via `setIdx(...)`
4. Update state `flipped` via `setFlipped(...)`
5. Update state `known` via `setKnown(...)`
6. Update state `unknown` via `setUnknown(...)`
7. Update state `done` via `setDone(...)`

## Flow 105: onClick in src/pages/ReviewChallenge.tsx:126

**Entry expression:** `() => setFlipped(!flipped)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:126` with expression `() => setFlipped(!flipped)`.
2. Inline handler expression executes.
3. Update state `flipped` via `setFlipped(...)`

## Flow 106: onClick in src/pages/ReviewChallenge.tsx:147

**Entry expression:** `() => handleNext(false)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:147` with expression `() => handleNext(false)`.
2. Inline handler expression executes.
3. Call local function `handleNext()`
4. Inside `handleNext()` (src/pages/ReviewChallenge.tsx:66).
5. Update state `known` via `setKnown(...)`
6. Update state `unknown` via `setUnknown(...)`
7. Update state `flipped` via `setFlipped(...)`
8. Update state `done` via `setDone(...)`
9. Update state `idx` via `setIdx(...)`

## Flow 107: onClick in src/pages/ReviewChallenge.tsx:151

**Entry expression:** `() => handleNext(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:151` with expression `() => handleNext(true)`.
2. Inline handler expression executes.
3. Call local function `handleNext()`
4. Inside `handleNext()` (src/pages/ReviewChallenge.tsx:66).
5. Update state `known` via `setKnown(...)`
6. Update state `unknown` via `setUnknown(...)`
7. Update state `flipped` via `setFlipped(...)`
8. Update state `done` via `setDone(...)`
9. Update state `idx` via `setIdx(...)`

## Flow 108: onClick in src/pages/ReviewChallenge.tsx:224

**Entry expression:** `handleReset`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:224` with expression `handleReset`.
2. Execute handler `handleReset()` (src/pages/ReviewChallenge.tsx:77).
3. Update state `idx` via `setIdx(...)`
4. Update state `flipped` via `setFlipped(...)`
5. Update state `known` via `setKnown(...)`
6. Update state `unknown` via `setUnknown(...)`
7. Update state `done` via `setDone(...)`

## Flow 109: onClick in src/pages/ReviewChallenge.tsx:260

**Entry expression:** `() => handleSelect(i)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:260` with expression `() => handleSelect(i)`.
2. Inline handler expression executes.
3. Call local function `handleSelect()`
4. Inside `handleSelect()` (src/pages/ReviewChallenge.tsx:200).
5. Update state `selected` via `setSelected(...)`
6. Call local function `goNext()`
7. Inside `goNext()` (src/pages/ReviewChallenge.tsx:189).
8. Update state `score` via `setScore(...)`
9. Update state `streak` via `setStreak(...)`
10. Update state `bestStreak` via `setBestStreak(...)`
11. Update state `streak` via `setStreak(...)`
12. Update state `done` via `setDone(...)`
13. Update state `qIdx` via `setQIdx(...)`
14. Update state `selected` via `setSelected(...)`
15. Update state `timeLeft` via `setTimeLeft(...)`

## Flow 110: onClick in src/pages/ReviewChallenge.tsx:336

**Entry expression:** `handleReset`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:336` with expression `handleReset`.
2. Execute handler `handleReset()` (src/pages/ReviewChallenge.tsx:77).
3. Update state `idx` via `setIdx(...)`
4. Update state `flipped` via `setFlipped(...)`
5. Update state `known` via `setKnown(...)`
6. Update state `unknown` via `setUnknown(...)`
7. Update state `done` via `setDone(...)`

## Flow 111: onClick in src/pages/ReviewChallenge.tsx:358

**Entry expression:** `() => handleSelect(item)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:358` with expression `() => handleSelect(item)`.
2. Inline handler expression executes.
3. Call local function `handleSelect()`
4. Inside `handleSelect()` (src/pages/ReviewChallenge.tsx:200).
5. Update state `selected` via `setSelected(...)`
6. Call local function `goNext()`
7. Inside `goNext()` (src/pages/ReviewChallenge.tsx:189).
8. Update state `score` via `setScore(...)`
9. Update state `streak` via `setStreak(...)`
10. Update state `bestStreak` via `setBestStreak(...)`
11. Update state `streak` via `setStreak(...)`
12. Update state `done` via `setDone(...)`
13. Update state `qIdx` via `setQIdx(...)`
14. Update state `selected` via `setSelected(...)`
15. Update state `timeLeft` via `setTimeLeft(...)`

## Flow 112: onClick in src/pages/ReviewChallenge.tsx:400

**Entry expression:** `() => setActiveMode(null)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:400` with expression `() => setActiveMode(null)`.
2. Inline handler expression executes.
3. Update state `activeMode` via `setActiveMode(...)`

## Flow 113: onClick in src/pages/ReviewChallenge.tsx:458

**Entry expression:** `() => setActiveMode(mode.id)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/ReviewChallenge.tsx:458` with expression `() => setActiveMode(mode.id)`.
2. Inline handler expression executes.
3. Update state `activeMode` via `setActiveMode(...)`

## Flow 114: onClick in src/pages/VocabularyPack.tsx:207

**Entry expression:** `() => handleSelect(idx)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:207` with expression `() => handleSelect(idx)`.
2. Inline handler expression executes.
3. Call local function `handleSelect()`
4. Inside `handleSelect()` (src/pages/VocabularyPack.tsx:195).
5. Update state `selected` via `setSelected(...)`
6. Update state `showResult` via `setShowResult(...)`

## Flow 115: onChange in src/pages/VocabularyPack.tsx:240

**Entry expression:** `e => setAnswer(e.target.value)`

### Chronological Steps

1. User triggers `onChange` on `src/pages/VocabularyPack.tsx:240` with expression `e => setAnswer(e.target.value)`.
2. Inline handler expression executes.
3. Update state `answer` via `setAnswer(...)`

## Flow 116: onKeyDown in src/pages/VocabularyPack.tsx:241

**Entry expression:** `e => e.key === "Enter" && handleSubmit()`

### Chronological Steps

1. User triggers `onKeyDown` on `src/pages/VocabularyPack.tsx:241` with expression `e => e.key === "Enter" && handleSubmit()`.
2. Inline handler expression executes.
3. Call local function `handleSubmit()`
4. Inside `handleSubmit()` (src/pages/VocabularyPack.tsx:231).
5. Update state `showResult` via `setShowResult(...)`

## Flow 117: onClick in src/pages/VocabularyPack.tsx:244

**Entry expression:** `handleSubmit`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:244` with expression `handleSubmit`.
2. Execute handler `handleSubmit()` (src/pages/VocabularyPack.tsx:231).
3. Update state `showResult` via `setShowResult(...)`

## Flow 118: onClick in src/pages/VocabularyPack.tsx:279

**Entry expression:** `handleScan`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:279` with expression `handleScan`.
2. Execute handler `handleScan()` (src/pages/VocabularyPack.tsx:260).
3. Update state `scanning` via `setScanning(...)`
4. Update state `scanning` via `setScanning(...)`

## Flow 119: onClick in src/pages/VocabularyPack.tsx:303

**Entry expression:** `onBack`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:303` with expression `onBack`.
2. Handler `onBack` is referenced but not resolved in this file.

## Flow 120: onClick in src/pages/VocabularyPack.tsx:333

**Entry expression:** `() => setShowVideo(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:333` with expression `() => setShowVideo(true)`.
2. Inline handler expression executes.
3. Update state `showVideo` via `setShowVideo(...)`

## Flow 121: onClick in src/pages/VocabularyPack.tsx:343

**Entry expression:** `() => lesson.quiz ? setPhase("quiz") : onComplete()`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:343` with expression `() => lesson.quiz ? setPhase("quiz") : onComplete()`.
2. Inline handler expression executes.
3. Update state `phase` via `setPhase(...)`

## Flow 122: onClose in src/pages/VocabularyPack.tsx:353

**Entry expression:** `() => setShowVideo(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/VocabularyPack.tsx:353` with expression `() => setShowVideo(false)`.
2. Inline handler expression executes.
3. Update state `showVideo` via `setShowVideo(...)`

## Flow 123: onComplete in src/pages/VocabularyPack.tsx:354

**Entry expression:** `() => setShowVideo(false)`

### Chronological Steps

1. User triggers `onComplete` on `src/pages/VocabularyPack.tsx:354` with expression `() => setShowVideo(false)`.
2. Inline handler expression executes.
3. Update state `showVideo` via `setShowVideo(...)`

## Flow 124: onClick in src/pages/VocabularyPack.tsx:363

**Entry expression:** `() => setShowVideo(!showVideo)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:363` with expression `() => setShowVideo(!showVideo)`.
2. Inline handler expression executes.
3. Update state `showVideo` via `setShowVideo(...)`

## Flow 125: onComplete in src/pages/VocabularyPack.tsx:373

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/pages/VocabularyPack.tsx:373` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/pages/VocabularyPack.tsx:296).
3. No state/API/routing/local-call side effects detected in `handleQuizComplete()`.

## Flow 126: onComplete in src/pages/VocabularyPack.tsx:374

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/pages/VocabularyPack.tsx:374` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/pages/VocabularyPack.tsx:296).
3. No state/API/routing/local-call side effects detected in `handleQuizComplete()`.

## Flow 127: onComplete in src/pages/VocabularyPack.tsx:375

**Entry expression:** `handleQuizComplete`

### Chronological Steps

1. User triggers `onComplete` on `src/pages/VocabularyPack.tsx:375` with expression `handleQuizComplete`.
2. Execute handler `handleQuizComplete()` (src/pages/VocabularyPack.tsx:296).
3. No state/API/routing/local-call side effects detected in `handleQuizComplete()`.

## Flow 128: onClose in src/pages/VocabularyPack.tsx:424

**Entry expression:** `() => setVideoLessonId(null)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/VocabularyPack.tsx:424` with expression `() => setVideoLessonId(null)`.
2. Inline handler expression executes.
3. Update state `videoLessonId` via `setVideoLessonId(...)`

## Flow 129: onBack in src/pages/VocabularyPack.tsx:446

**Entry expression:** `() => setActiveLessonIdx(null)`

### Chronological Steps

1. User triggers `onBack` on `src/pages/VocabularyPack.tsx:446` with expression `() => setActiveLessonIdx(null)`.
2. Inline handler expression executes.
3. Update state `activeLessonIdx` via `setActiveLessonIdx(...)`

## Flow 130: onComplete in src/pages/VocabularyPack.tsx:447

**Entry expression:** `() => handleLessonComplete(lesson.id, activeLessonIdx)`

### Chronological Steps

1. User triggers `onComplete` on `src/pages/VocabularyPack.tsx:447` with expression `() => handleLessonComplete(lesson.id, activeLessonIdx)`.
2. Inline handler expression executes.
3. Call local function `handleLessonComplete()`
4. Inside `handleLessonComplete()` (src/pages/VocabularyPack.tsx:396).
5. Update state `activeLessonIdx` via `setActiveLessonIdx(...)`
6. Update state `activeLessonIdx` via `setActiveLessonIdx(...)`

## Flow 131: onClick in src/pages/VocabularyPack.tsx:457

**Entry expression:** `onBack`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:457` with expression `onBack`.
2. Handler `onBack` is referenced but not resolved in this file.

## Flow 132: onClick in src/pages/VocabularyPack.tsx:535

**Entry expression:** `() => startLesson(chapter.lessons[firstIncomplete], firstIncomplete)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:535` with expression `() => startLesson(chapter.lessons[firstIncomplete], firstIncomplete)`.
2. Inline handler expression executes.
3. Call local function `startLesson()`
4. Inside `startLesson()` (src/pages/VocabularyPack.tsx:406).
5. Update state `videoLessonId` via `setVideoLessonId(...)`
6. Update state `activeLessonIdx` via `setActiveLessonIdx(...)`

## Flow 133: onClose in src/pages/VocabularyPack.tsx:544

**Entry expression:** `() => setPremiumOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/VocabularyPack.tsx:544` with expression `() => setPremiumOpen(false)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 134: onClick in src/pages/VocabularyPack.tsx:571

**Entry expression:** `() => setPremiumOpen(true)`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:571` with expression `() => setPremiumOpen(true)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 135: onClick in src/pages/VocabularyPack.tsx:575

**Entry expression:** `onGoNext`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:575` with expression `onGoNext`.
2. Handler `onGoNext` is referenced but not resolved in this file.

## Flow 136: onClick in src/pages/VocabularyPack.tsx:580

**Entry expression:** `onBackToUnit`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:580` with expression `onBackToUnit`.
2. Handler `onBackToUnit` is referenced but not resolved in this file.

## Flow 137: onClose in src/pages/VocabularyPack.tsx:585

**Entry expression:** `() => setPremiumOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/VocabularyPack.tsx:585` with expression `() => setPremiumOpen(false)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 138: onGoNext in src/pages/VocabularyPack.tsx:611

**Entry expression:** `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(completedChapterIdx + 1);`

### Chronological Steps

1. User triggers `onGoNext` on `src/pages/VocabularyPack.tsx:611` with expression `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(completedChapterIdx + 1);`.
2. Inline handler expression executes.
3. Update state `completedChapterIdx` via `setCompletedChapterIdx(...)`
4. Update state `selectedChapterIdx` via `setSelectedChapterIdx(...)`

## Flow 139: onBackToUnit in src/pages/VocabularyPack.tsx:612

**Entry expression:** `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(null);`

### Chronological Steps

1. User triggers `onBackToUnit` on `src/pages/VocabularyPack.tsx:612` with expression `() => { setCompletedChapterIdx(null); setSelectedChapterIdx(null);`.
2. Inline handler expression executes.
3. Update state `completedChapterIdx` via `setCompletedChapterIdx(...)`
4. Update state `selectedChapterIdx` via `setSelectedChapterIdx(...)`

## Flow 140: onBack in src/pages/VocabularyPack.tsx:624

**Entry expression:** `() => setSelectedChapterIdx(null)`

### Chronological Steps

1. User triggers `onBack` on `src/pages/VocabularyPack.tsx:624` with expression `() => setSelectedChapterIdx(null)`.
2. Inline handler expression executes.
3. Update state `selectedChapterIdx` via `setSelectedChapterIdx(...)`

## Flow 141: onChapterComplete in src/pages/VocabularyPack.tsx:625

**Entry expression:** `() => setCompletedChapterIdx(selectedChapterIdx)`

### Chronological Steps

1. User triggers `onChapterComplete` on `src/pages/VocabularyPack.tsx:625` with expression `() => setCompletedChapterIdx(selectedChapterIdx)`.
2. Inline handler expression executes.
3. Update state `completedChapterIdx` via `setCompletedChapterIdx(...)`

## Flow 142: onClick in src/pages/VocabularyPack.tsx:637

**Entry expression:** `onBack`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:637` with expression `onBack`.
2. Handler `onBack` is referenced but not resolved in this file.

## Flow 143: onClick in src/pages/VocabularyPack.tsx:708

**Entry expression:** `e => { e.stopPropagation(); setSelectedChapterIdx(idx);`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:708` with expression `e => { e.stopPropagation(); setSelectedChapterIdx(idx);`.
2. Inline handler expression executes.
3. Update state `selectedChapterIdx` via `setSelectedChapterIdx(...)`

## Flow 144: onClose in src/pages/VocabularyPack.tsx:724

**Entry expression:** `() => setPremiumOpen(false)`

### Chronological Steps

1. User triggers `onClose` on `src/pages/VocabularyPack.tsx:724` with expression `() => setPremiumOpen(false)`.
2. Inline handler expression executes.
3. Update state `premiumOpen` via `setPremiumOpen(...)`

## Flow 145: onBack in src/pages/VocabularyPack.tsx:753

**Entry expression:** `() => setState({ view: "units"`

### Chronological Steps

1. User triggers `onBack` on `src/pages/VocabularyPack.tsx:753` with expression `() => setState({ view: "units"`.
2. Inline handler expression executes.
3. Update state `state` via `setState(...)`

## Flow 146: onClick in src/pages/VocabularyPack.tsx:783

**Entry expression:** `() => setState({ view: "unit", unitIdx: i`

### Chronological Steps

1. User triggers `onClick` on `src/pages/VocabularyPack.tsx:783` with expression `() => setState({ view: "unit", unitIdx: i`.
2. Inline handler expression executes.
3. Update state `state` via `setState(...)`
