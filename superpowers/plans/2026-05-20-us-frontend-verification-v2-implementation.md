# US Frontend Verification v2 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Build `docs/US_Frontend_Verification_Report_v2.md` with code-driven verification and actionable solutions for all US gaps/mismatches, including release-phase and effort planning.

**Architecture:** The report is built in one canonical markdown file using evidence from `docs/EXE101_FE_Business_Flows.md` and `src/**` references. Work is executed epic-by-epic to keep decisions auditable and reversible. Each epic block includes status classification (`Matched`, `Mismatch`, `Not Implemented`) and remediation actions with `Priority`, `Release`, and `Effort`.

**Tech Stack:** Markdown docs, PowerShell, `rg`, git

---

## File Structure

- Create: `docs/US_Frontend_Verification_Report_v2.md`
  - Purpose: final v2 verification + remediation document (single source of truth).
- Create: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`
  - Purpose: intermediate evidence notes and classification rationale before final polish.
- Modify: `docs/superpowers/specs/2026-05-20-us-frontend-verification-v2-design.md` (optional, only if implementation uncovers required design clarifications).

## Task 1: Scaffold v2 Report and Working Notes

**Files:**
- Create: `docs/US_Frontend_Verification_Report_v2.md`
- Create: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`

- [ ] **Step 1: Create working directory and files**

Run:

```powershell
if (!(Test-Path "docs/superpowers/working")) { New-Item -ItemType Directory -Path "docs/superpowers/working" -Force | Out-Null }
New-Item -ItemType File -Path "docs/US_Frontend_Verification_Report_v2.md" -Force | Out-Null
New-Item -ItemType File -Path "docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md" -Force | Out-Null
```

Expected: both files exist.

- [ ] **Step 2: Write report header and matrix schema**

Insert this initial structure into `docs/US_Frontend_Verification_Report_v2.md`:

```markdown
# V-SIGN User Stories vs Frontend Verification Report v2 (Code-Driven)

## Verification Scope
- Primary evidence: `docs/EXE101_FE_Business_Flows.md`
- Secondary evidence: `src/**`
- Legacy comparison: `docs/US_Frontend_Verification_Report.md`

## Classification Rules
- Matched
- Mismatch
- Not Implemented

## US-by-US Remediation Matrix

| US ID | US Name | Current Frontend State | Evidence | Gap Type | Root Cause | Solution - Frontend | Solution - US/AC | Priority | Release | Effort | Dependencies | Confidence |
|---|---|---|---|---|---|---|---|---|---|---|---|---|
```

- [ ] **Step 3: Validate scaffold**

Run:

```powershell
rg -n "US-by-US Remediation Matrix|Classification Rules|Gap Type|Effort|Confidence" docs/US_Frontend_Verification_Report_v2.md
```

Expected: all headings/column tokens found.

- [ ] **Step 4: Commit scaffold**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: scaffold US frontend verification report v2"
```

## Task 2: Implement Epic 1-3 Reclassification (US-01 to US-32)

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`
- Modify: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`
- Evidence: `docs/EXE101_FE_Business_Flows.md`, `src/components/LoginModal.tsx`, `src/pages/Onboarding.tsx`, `src/pages/MockExam.tsx`, `src/pages/Dashboard.tsx`, `src/pages/VocabularyPack.tsx`

- [ ] **Step 1: Add Epic 1-3 section headers in v2 report**

Add:

```markdown
### Epic 1: Authentication & Profile (US-01..US-08)
### Epic 2: Content Management (US-09..US-18)
### Epic 3: Assessment (US-19..US-32)
```

- [ ] **Step 2: Fill rows for US-01..US-32 using classification rules**

Use one row format per story:

```markdown
| US-17 | Redirect Paywall khi click Chapter Premium | Premium gate exists with modal behavior | VocabularyPack flow + PremiumModal usage | Mismatch | Story wording says redirect page | Keep modal flow, add lock-state UX hints and direct upgrade CTA | Update AC to modal overlay behavior | Must | v1.1 | S | None | High |
```

- [ ] **Step 3: Record rationale notes for all non-matched rows (US-01..US-32)**

In `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`, use:

```markdown
## US-24
- Classification: Mismatch
- Why: Auto-submit exists in `MockExam` timer effect but missing explicit UX warning state.
- FE Solution: Add 60s warning banner + autosubmit toast.
- US/AC Solution: Clarify auto-submit and warning AC.
- Release/Effort: v1.1 / S
```

- [ ] **Step 4: Validate row coverage for US-01..US-32**

Run:

```powershell
$c = (rg -n "^\| US-(0[1-9]|1[0-9]|2[0-9]|3[0-2]) \|" docs/US_Frontend_Verification_Report_v2.md | Measure-Object).Count; Write-Output $c
```

Expected: `32`.

- [ ] **Step 5: Commit Epic 1-3**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: classify and remediate US-01 to US-32"
```

## Task 3: Implement Epic 4 Reclassification (US-33 to US-49)

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`
- Modify: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`
- Evidence: `src/contexts/AuthContext.tsx`, `src/pages/Profile.tsx`, `src/pages/Leaderboard.tsx`, `src/pages/VocabularyPack.tsx`, `docs/EXE101_FE_Business_Flows.md`

- [ ] **Step 1: Add Epic 4 heading and complete US-33..US-49 rows**

Add heading:

```markdown
### Epic 4: Gamification (US-33..US-49)
```

- [ ] **Step 2: Ensure every non-matched US has implementation-ready FE solution**

For each `Not Implemented` or `Mismatch`, include concrete FE targets (example):

```markdown
Solution - Frontend: Add `longestStreak` field to `AuthContext` stats model, update `completeLesson`/day-rollover logic, render stat card in `Profile`.
```

- [ ] **Step 3: Validate row coverage for US-33..US-49**

Run:

```powershell
$c = (rg -n "^\| US-(3[3-9]|4[0-9]) \|" docs/US_Frontend_Verification_Report_v2.md | Measure-Object).Count; Write-Output $c
```

Expected: `17`.

- [ ] **Step 4: Commit Epic 4**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: classify and remediate US-33 to US-49"
```

## Task 4: Implement Epic 5-6 Reclassification (US-50 to US-64)

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`
- Modify: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`
- Evidence: `src/pages/Dictionary.tsx`, `src/components/PremiumModal.tsx`, `src/pages/Dashboard.tsx`, `docs/EXE101_FE_Business_Flows.md`

- [ ] **Step 1: Add Epic 5-6 headings and rows**

Add headings:

```markdown
### Epic 5: VSL Dictionary (US-50..US-55)
### Epic 6: Monetization (US-56..US-64)
```

- [ ] **Step 2: Define roadmap solutions for missing dictionary/payment UX features**

Use concrete solution language (example):

```markdown
US-55 FE solution: Add "Practice this sign" CTA in dictionary detail modal; route to `Dashboard` with `defaultTab="mock-exam"` and preselected sign context state.
```

- [ ] **Step 3: Validate row coverage for US-50..US-64**

Run:

```powershell
$c = (rg -n "^\| US-(5[0-9]|6[0-4]) \|" docs/US_Frontend_Verification_Report_v2.md | Measure-Object).Count; Write-Output $c
```

Expected: `15`.

- [ ] **Step 4: Commit Epic 5-6**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: classify and remediate US-50 to US-64"
```

## Task 5: Implement Epic 7 Reclassification (US-65 to US-75)

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`
- Modify: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md`
- Evidence: `src/App.tsx`, `src/pages/Dashboard.tsx`, `docs/EXE101_FE_Business_Flows.md`

- [ ] **Step 1: Add Epic 7 heading and rows**

Add heading:

```markdown
### Epic 7: Admin Panel (US-65..US-75)
```

- [ ] **Step 2: Classify admin stories and provide phase-2 implementation blueprint**

For each US-65..US-75, include:

```markdown
Release: phase-2 admin
Solution - Frontend: Create `/admin` route, role guard, admin navigation shell, and module-specific screens (Units, Chapters, Dictionary, Transactions, KPI).
Dependencies: Backend admin APIs + RBAC
```

- [ ] **Step 3: Validate row coverage for US-65..US-75**

Run:

```powershell
$c = (rg -n "^\| US-(6[5-9]|7[0-5]) \|" docs/US_Frontend_Verification_Report_v2.md | Measure-Object).Count; Write-Output $c
```

Expected: `11`.

- [ ] **Step 4: Commit Epic 7**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: classify and remediate US-65 to US-75"
```

## Task 6: Add Prioritized Release Backlog and Delivery Waves

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`

- [ ] **Step 1: Add release backlog section**

Insert:

```markdown
## Prioritized Delivery Backlog

### Wave 1 (v1.1)
- US-17, US-18, US-21, US-22, US-24, US-25, US-26
- US-34, US-35, US-38, US-39, US-40
- US-43, US-44, US-46, US-47, US-48
- US-54, US-55, US-61, US-62, US-63

### Wave 2 (v2)
- US-29, US-30, US-31, US-41

### Wave 3 (phase-2 admin)
- US-65, US-66, US-67, US-68, US-69, US-70, US-71, US-72, US-73, US-74, US-75
```

- [ ] **Step 2: Add effort summary table**

Insert:

```markdown
| Release | Must | Should | Could | S | M | L |
|---|---:|---:|---:|---:|---:|---:|
| v1.1 |  |  |  |  |  |  |
| v2 |  |  |  |  |  |  |
| phase-2 admin |  |  |  |  |  |  |
```

- [ ] **Step 3: Add execution sequencing notes**

Insert:

```markdown
## Execution Notes
1. Implement Mismatch fixes in current active modules first.
2. Implement Not Implemented user-facing features before admin features.
3. Keep US/AC updates synchronized with FE behavior changes.
```

- [ ] **Step 4: Commit backlog section**

```bash
git add docs/US_Frontend_Verification_Report_v2.md
git commit -m "docs: add release-prioritized remediation backlog"
```

## Task 7: Final Quality Gate and Publish

**Files:**
- Modify: `docs/US_Frontend_Verification_Report_v2.md`
- Modify: `docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md` (optional final notes)

- [ ] **Step 1: Validate total US row count = 75**

Run:

```powershell
$c = (rg -n "^\| US-[0-9]{2} \|" docs/US_Frontend_Verification_Report_v2.md | Measure-Object).Count; Write-Output $c
```

Expected: `75`.

- [ ] **Step 2: Validate every non-matched row has Release and Effort**

Run:

```powershell
rg -n "Mismatch|Not Implemented" docs/US_Frontend_Verification_Report_v2.md
rg -n "^\| US-[0-9]{2} \|.*\| (Mismatch|Not Implemented) \|.*\| (Must|Should|Could) \| (v1\\.1|v2|phase-2 admin) \| (S|M|L) \|" docs/US_Frontend_Verification_Report_v2.md
```

Expected: second command returns same number of rows as first (or documented variance explained inline).

- [ ] **Step 3: Placeholder scan**

Run:

```powershell
rg -n "TBD|TODO|fix later|placeholder|\\[US IDs\\.\\.\\.\\]" docs/US_Frontend_Verification_Report_v2.md
```

Expected: no matches.

- [ ] **Step 4: Final commit**

```bash
git add docs/US_Frontend_Verification_Report_v2.md docs/superpowers/working/2026-05-20-us-v2-evidence-notes.md
git commit -m "docs: publish code-driven US frontend verification report v2"
```

- [ ] **Step 5: Share completion summary**

Report:
- final status counts (`Matched`, `Mismatch`, `Not Implemented`)
- top v1.1 implementation candidates
- top risks and dependencies
