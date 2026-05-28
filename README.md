# SimilarityChecker

## Purpose

SimilarityChecker is a Java library for calculating a string similarity score.

The score range is 0 to 100.

## Score Composition

- Length score: max 60
- Alphabet score: max 40
- Final score: length score + alphabet score

## Branch Plan

1. Implement length similarity scoring on `feature/length`.
2. Merge `feature/length` into `main`.
3. Create `feature/alpha` from the updated `main`.
4. Implement alphabet similarity scoring and the final score API on `feature/alpha`.
5. Merge `feature/alpha` into `main`.

## Input Assumption

Input strings are uppercase `A` to `Z` strings.

## Run Tests

```bash
./gradlew test
```

## Pull Request Note

This repository is prepared for local branch and commit based work. The `gh` CLI is not available in the current environment, so pull requests are not created automatically.

After connecting a GitHub remote and authenticating locally, create pull requests from GitHub or with:

```bash
gh pr create --base main --head feature/length
gh pr create --base main --head feature/alpha
```
