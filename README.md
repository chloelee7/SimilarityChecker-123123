# SimilarityChecker

## Purpose

SimilarityChecker is a Java library for calculating a string similarity score.

The score range is 0 to 100.

## Score Composition

- Length score: max 60
- Alphabet score: max 40
- Final score: length score + alphabet score

## Length Score

Length score checks the difference between the two string lengths.

- If the lengths are the same, the score is 60.
- If the longer length is at least twice the shorter length, the score is 0.
- Otherwise, the partial score is calculated with:

```text
A = longer string length
B = shorter string length
Gap = A - B
lengthScore = (1 - Gap / B) * 60
```

Examples:

- `"ASD"`, `"DSA"` => 60
- `"A"`, `"BB"` => 0
- `"AAABB"`, `"BAA"` => 20
- `"AA"`, `"AAE"` => 30

## Alphabet Score

Alphabet score checks the kinds of uppercase alphabet letters used by the two strings.
Duplicate letters do not affect the score.

- If the alphabet kinds are the same, the score is 40.
- If there is no shared alphabet kind, the score is 0.
- Otherwise, the partial score is calculated with:

```text
TotalCnt = count of alphabet kinds in the union
SameCnt = count of alphabet kinds in the intersection
alphaScore = SameCnt / TotalCnt * 40
```

Examples:

- `"ASD"`, `"DSA"` => 40
- `"A"`, `"BB"` => 0
- `"AAABB"`, `"BA"` => 40
- `"AA"`, `"AAE"` => 20

## Final Score

The final score is calculated by adding the length score and alphabet score.

```text
score = lengthScore + alphabetScore
```

Examples:

- `"ABCDE"`, `"ABCDE"` => 100
- `"ASD"`, `"DSA"` => 100
- `"A"`, `"BB"` => 0
- `"AAABB"`, `"BAA"` => 60
  - lengthScore 20 + alphabetScore 40
- `"AA"`, `"AAE"` => 50
  - lengthScore 30 + alphabetScore 20

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
