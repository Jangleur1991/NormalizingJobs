package com.feefo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QualityScoreCalculatorTest {
    private static final String NORMALIZED_WORD = "Software engineer";
    private QualityScoreCalculator testCandidate;
    @Mock
    private QualityScoreAlgorithm qualityScoreAlgorithm;

    @BeforeEach
    public void setUp() {
        testCandidate = new QualityScoreCalculator(NORMALIZED_WORD);
        testCandidate.changeQualityScoreAlgorithm(qualityScoreAlgorithm);
    }

    @Test
    public void testCalculateQualityScore() {
        //given
        String jobTitle = "Java engineer";
        double expectedResult = 0.6;
        when(qualityScoreAlgorithm.calculateQualityScore(jobTitle, NORMALIZED_WORD)).thenReturn(expectedResult);

        //when
        double result = testCandidate.calcQualityScore(jobTitle);

        //then
        assertEquals(expectedResult, result);
    }
}