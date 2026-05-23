## Objective

Produce a concise delivery summary for "Delivery report".

## Summary

- The orchestrator can accept one large request and convert it into parallel worker tasks.
- Skill metadata drives task-to-template mapping and report merge order.
- Output artifacts are written as isolated Markdown files before deterministic merge.

## Decisions

- Build the core in the FE repo because it already contains project-local Codex assets.
- Use Node subprocesses as async workers to avoid introducing more infrastructure.
- Keep the MVP local-first and deterministic rather than pretending to call unavailable LLM backends.

## Next Actions

- Add an external worker adapter if the team later wants real AI execution.
- Extend task decomposition rules for domain-specific workflows.
- Add richer repository scanners if the orchestrator becomes a regular delivery tool.
