package com.example.similaritychecker;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SimilarityCheckerTest {
    private static final double TOLERANCE = 0.0001;

    private SimilarityChecker checker = new SimilarityChecker();

    @BeforeEach
    void setUp() {
        checker = new SimilarityChecker();
    }

    @ParameterizedTest
    @CsvSource({
            "ASD, DSA",
            "ABCDE, VWXYZ"
    })
    void returns_sixty_points_when_lengths_are_same(String left, String right) {
        assertThat(checker.lengthScore(left, right)).isCloseTo(60.0, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "A, BB",
            "AB, ABCD"
    })
    void returns_zero_points_when_one_length_is_at_least_twice_the_other(String left, String right) {
        assertThat(checker.lengthScore(left, right)).isCloseTo(0.0, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "AA, AAE, 30.0",
            "AAABB, BAA, 20.0"
    })
    void returns_partial_points_for_length_gap_smaller_than_the_shorter_length(
            String left,
            String right,
            double expectedScore
    ) {
        assertThat(checker.lengthScore(left, right)).isCloseTo(expectedScore, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "ASD, DSA",
            "AAABB, BA"
    })
    void returns_forty_points_when_alphabet_kinds_are_same(String left, String right) {
        assertThat(checker.alphabetScore(left, right)).isCloseTo(40.0, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "A, BB"
    })
    void returns_zero_points_when_alphabet_kinds_are_all_different(String left, String right) {
        assertThat(checker.alphabetScore(left, right)).isCloseTo(0.0, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "AA, AAE, 20.0",
            "ABC, BCD, 20.0"
    })
    void returns_partial_points_for_partially_overlapping_alphabet_kinds(
            String left,
            String right,
            double expectedScore
    ) {
        assertThat(checker.alphabetScore(left, right)).isCloseTo(expectedScore, within(TOLERANCE));
    }

    @ParameterizedTest
    @CsvSource({
            "ABCDE, ABCDE, 100.0",
            "ASD, DSA, 100.0",
            "A, BB, 0.0",
            "AAABB, BAA, 60.0",
            "AA, AAE, 50.0"
    })
    void returns_the_sum_of_length_score_and_alphabet_score(
            String left,
            String right,
            double expectedScore
    ) {
        assertThat(checker.score(left, right)).isCloseTo(expectedScore, within(TOLERANCE));
    }
}
