## Objective

Review operational risks and validation gates for "Validation and risk review".

## Risks

- Missing skill metadata can break task mapping.
- Worker output drift can make the final merge nondeterministic.
- External-agent dependencies would make smoke testing flaky.

## Validation Plan

- Validate required Markdown headings for every worker result.
- Run an orchestrator smoke command that exercises parallel subprocess execution.
- Cover request decomposition and merge ordering with Vitest.

## Mitigations

- Fail fast when a skill directory is missing `skill.json` or `SKILL.md`.
- Keep worker generation deterministic and template-driven.
- Default to local internal workers and make future external adapters optional.
