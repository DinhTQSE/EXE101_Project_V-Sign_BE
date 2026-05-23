## Objective

Define the repo-native orchestration path for "Architecture proposal".

## Constraints

- Must run locally with current Node tooling.
- Must not depend on backend services or network calls.
- Must expose a simple project CLI entrypoint and reusable templates.

## Proposed Architecture

- `cli.mjs` parses commands and creates the run directory.
- `registry.mjs` loads project skill metadata from `skills/`.
- `planner.mjs` decomposes the top-level request into deterministic worker tasks.
- `orchestrator.mjs` spawns parallel worker subprocesses and validates outputs before merge.

## Execution Notes

- Each worker receives isolated JSON input and writes one Markdown file plus a JSON manifest.
- Internal worker mode is the default MVP because it is stable for CI and smoke tests.
- The final report is merged in a fixed `mergeOrder` derived from skill metadata.
