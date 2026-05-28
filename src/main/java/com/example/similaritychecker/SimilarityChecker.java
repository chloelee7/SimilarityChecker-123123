package com.example.similaritychecker;

public class SimilarityChecker {
    private static final double LENGTH_MAX_SCORE = 60.0;
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
}
