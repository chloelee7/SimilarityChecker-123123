package com.example.similaritychecker;

import java.util.HashSet;
import java.util.Set;

public class SimilarityChecker {
    private static final double LENGTH_MAX_SCORE = 60.0;
    private static final double ALPHABET_MAX_SCORE = 40.0;
    private static final double ZERO_SCORE = 0.0;

    public double lengthScore(String left, String right) {
        int leftLength = left.length();
        int rightLength = right.length();

        if (hasSameLength(leftLength, rightLength)) {
            return LENGTH_MAX_SCORE;
        }

        int longerLength = Math.max(leftLength, rightLength);
        int shorterLength = Math.min(leftLength, rightLength);

        if (isAtLeastTwiceAsLong(longerLength, shorterLength)) {
            return ZERO_SCORE;
        }

        return calculatePartialLengthScore(longerLength, shorterLength);
    }

    private boolean hasSameLength(int leftLength, int rightLength) {
        return leftLength == rightLength;
    }

    private boolean isAtLeastTwiceAsLong(int longerLength, int shorterLength) {
        return longerLength >= shorterLength * 2;
    }

    private double calculatePartialLengthScore(int longerLength, int shorterLength) {
        int gap = longerLength - shorterLength;
        return (1.0 - (double) gap / shorterLength) * LENGTH_MAX_SCORE;
    }

    public double alphabetScore(String left, String right) {
        Set<Character> leftAlphabetKinds = alphabetKindsOf(left);
        Set<Character> rightAlphabetKinds = alphabetKindsOf(right);

        int totalCount = countTotalAlphabetKinds(leftAlphabetKinds, rightAlphabetKinds);
        int sameCount = countSameAlphabetKinds(leftAlphabetKinds, rightAlphabetKinds);

        return calculateAlphabetScore(totalCount, sameCount);
    }

    private int countTotalAlphabetKinds(Set<Character> leftAlphabetKinds, Set<Character> rightAlphabetKinds) {
        Set<Character> totalAlphabetKinds = new HashSet<>(leftAlphabetKinds);
        totalAlphabetKinds.addAll(rightAlphabetKinds);

        return totalAlphabetKinds.size();
    }

    private int countSameAlphabetKinds(Set<Character> leftAlphabetKinds, Set<Character> rightAlphabetKinds) {
        Set<Character> sameAlphabetKinds = new HashSet<>(leftAlphabetKinds);
        sameAlphabetKinds.retainAll(rightAlphabetKinds);

        return sameAlphabetKinds.size();
    }

    private double calculateAlphabetScore(int totalCount, int sameCount) {
        return (double) sameCount / totalCount * ALPHABET_MAX_SCORE;
    }

    private Set<Character> alphabetKindsOf(String value) {
        Set<Character> alphabetKinds = new HashSet<>();

        for (char letter : value.toCharArray()) {
            alphabetKinds.add(letter);
        }

        return alphabetKinds;
    }
}
