package com.feefo;

import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Objects;

public class QualityScoreLevenshtein implements QualityScoreAlgorithm {

    private final LevenshteinDistance levenshteinDistance;

    public QualityScoreLevenshtein() {
        this.levenshteinDistance = LevenshteinDistance.getDefaultInstance();
    }

    @Override
    public double calculateQualityScore(String word, String normalizedWord) {
        Objects.requireNonNull(word);
        Objects.requireNonNull(normalizedWord);
        return !word.equals(normalizedWord)
                ? calcLevenshteinScore(word, normalizedWord)
                : 1.0;
    }

    private double calcLevenshteinScore(String word, String normalizedWord) {
        Integer currentQualityScore = levenshteinDistance.apply(word, normalizedWord);
        return secondDecimal(1.0 - ((double) currentQualityScore) / Math.max(word.length(), normalizedWord.length()));
    }

    private double secondDecimal(double number) {
        return Math.floor(number*100)/100;
    }
}
