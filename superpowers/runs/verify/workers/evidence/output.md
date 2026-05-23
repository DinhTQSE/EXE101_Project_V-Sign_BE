## Objective

Map repo facts and constraints for "Repository evidence scan".

## Repository Evidence

- The FE repo already contains `.codex/`, `skills/`, `docs/superpowers/`, and npm scripts.
- The stack is Vite + React + TypeScript, which makes a Node-based sidecar CLI a natural fit.
- Task goal: Extract the repo-native constraints, existing systems, and reusable assets relevant to the request.
- Mapped skill: Superpower Evidence Synthesizer
- Request keywords: verify, async, multi-agent, orchestrator, local, deterministic, workers

## Findings

- No existing orchestrator runtime or agent runner was found in the repo.
- Existing docs already use the `docs/superpowers/` namespace, so output should land there.
- A deterministic local worker is required for smoke testing without external AI dependencies.

## Recommended Inputs

- Skill metadata from `skills/*/skill.json`
- User request text provided to the orchestrator
- Output directory under `docs/superpowers/runs/`
