package com.feefo;

public interface QualityScoreAlgorithm {
    double calculateQualityScore(String word, String normalizedWord);
}
