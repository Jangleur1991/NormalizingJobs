package com.feefo;

public class QualityScoreCalculator {
    private final String normalizedWord;
    private QualityScoreAlgorithm qualityScoreAlgorithm;

    public QualityScoreCalculator(String normalizedWord) {
        this.normalizedWord = normalizedWord;
        this.qualityScoreAlgorithm = new QualityScoreLevenshtein();
    }

    public void changeQualityScoreAlgorithm(QualityScoreAlgorithm algorithm) {
        this.qualityScoreAlgorithm = algorithm;
    }

    public double calcQualityScore(String word) {
        return qualityScoreAlgorithm.calculateQualityScore(word, normalizedWord);
    }

    public String getNormalizedWord() {
        return this.normalizedWord;
    }

}
