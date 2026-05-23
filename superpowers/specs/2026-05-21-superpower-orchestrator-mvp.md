# Superpower Orchestrator MVP

## Intent

Provide a repo-native local orchestrator that turns one large request into parallel, template-backed worker jobs and merges their outputs into a deterministic Markdown report.

## Placement

- `skills/` stores the reusable Superpower templates and metadata.
- `scripts/superpowers/` stores the local Node runtime because this repo already uses npm, Vite, and Vitest.
- `docs/superpowers/runs/` stores generated run artifacts.

## MVP Scope

- Deterministic request decomposition into four task slices: evidence, architecture, risk, and report.
- Parallel worker subprocess execution using Node child processes.
- Per-worker isolated inputs, outputs, and manifests.
- Required-section validation before merge.
- Final report and run manifest emission.

## Non-Goals

- Real LLM provider integration.
- Dynamic project graph analysis.
- User-interactive planning loops.

## Upgrade Path

- Add external worker adapters while keeping the same task manifest contract.
- Expand task decomposition rules for domain-specific workflows.
- Add repository scanners that enrich worker inputs with file references.
