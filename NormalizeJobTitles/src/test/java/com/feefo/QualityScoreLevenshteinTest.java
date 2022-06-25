package com.feefo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QualityScoreLevenshteinTest {

    private static final String NORMALIZED_WORD = "Architect";
    private QualityScoreLevenshtein testCandidate;

    @BeforeEach
    public void setUp() {
        testCandidate = new QualityScoreLevenshtein();
    }

    @Test
    public void testThatCalculateQualityScoreWithNullValuesThrowNPE() {
        //given
        String jobTitle = null;

        //then
        assertThrows(NullPointerException.class, () -> {
            //when
            testCandidate.calculateQualityScore(jobTitle, NORMALIZED_WORD);
        });

        //then
        assertThrows(NullPointerException.class, () -> {
            //when
            testCandidate.calculateQualityScore(null, jobTitle);
        });

    }

    @Test
    public void testThatCalculateQualityScoreWithPerfectMatchReturnsOne() {
        //given
        String jobTitle = NORMALIZED_WORD;

        //when
        double result = testCandidate.calculateQualityScore(jobTitle, NORMALIZED_WORD);

        //then
        assertEquals(result, 1.0);
    }

    @Test
    public void testThatCalculateQualityScoreWithTotalDifferentWordsReturnsZero() {
        //given
        String jobTitle = "";

        //when
        double result = testCandidate.calculateQualityScore(jobTitle, NORMALIZED_WORD);

        //then
        assertEquals(result, 0.0);
    }

    @Test
    public void testThatCalculateQualityScoreWith20PercentDifferenceReturns80Percent() {
        //given
        String word = "de1fg";
        String idealWord = "de2fg";

        //when
        double result = testCandidate.calculateQualityScore(word, idealWord);

        //then
        assertEquals(result, 0.8);
    }


    @Test
    public void testThatCalculateQualityScoreWith50PercentDifferenceReturns50Percent() {
        //given
        String word = "ab";
        String idealWord = "ac";

        //when
        double result = testCandidate.calculateQualityScore(word, idealWord);

        //then
        assertEquals(result, 0.5);
    }

    @Test
    public void testThatCalculateQualityScoreWith25PercentDifferenceReturns75Percent() {
        //given
        String word = "abcd";
        String idealWord = "abed";

        //when
        double result = testCandidate.calculateQualityScore(word, idealWord);

        //then
        assertEquals(result, 0.75);
    }

}