package com.feefo;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Normaliser {
    private List<QualityScoreCalculator> qualityScoreCalculatorList;

    public Normaliser() {
        //Setting default List of normalized Words just because
        //it's needed in Task example...
        this(List.of(
                "Architect",
                "Software engineer",
                "Quantity surveyor",
                "Accountant"
        ));
    }

    public Normaliser(List<String> normalizedWords) {
        changeNormalizedWords(normalizedWords);
    }

    public void changeNormalizedWords(List<String> normalizedWords) {
        Objects.requireNonNull(normalizedWords);
        this.qualityScoreCalculatorList = createQualityScoreCalculatorList(normalizedWords);
    }

    private List<QualityScoreCalculator> createQualityScoreCalculatorList(List<String> normalizedWords) {
        return normalizedWords.stream() //
                .filter(Objects::nonNull) //
                .map(QualityScoreCalculator::new) //
                .toList();
    }

    public String normalise(String word) {
        return StringUtils.isNotEmpty(word)
                    ? calcNormalizedWord(word)
                    : "";
    }

    private String calcNormalizedWord(String word) {
        Comparator<QualityScoreCalculator> qualityScoreComparator = createQualityScoreComparator(word);
        return qualityScoreCalculatorList.stream() //
                .max(qualityScoreComparator) //
                .map(QualityScoreCalculator::getNormalizedWord) //
                .orElse("");
    }

    private Comparator<QualityScoreCalculator> createQualityScoreComparator(String word) {
        return Comparator.comparing(qualityScoreCalculator -> qualityScoreCalculator.calcQualityScore(word));
    }

    public void addNormalizedWord(String normalizedWord) {
        this.qualityScoreCalculatorList.add(new QualityScoreCalculator(normalizedWord));
    }

    public void changeQualityScoreAlgorithm(QualityScoreAlgorithm algorithm) {
        qualityScoreCalculatorList.forEach(qualityScoreCalculator ->
                qualityScoreCalculator.changeQualityScoreAlgorithm(algorithm));
    }

}
