package com.feefo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NormaliserTest {

    private Normaliser testCandidate;

    @BeforeEach
    public void setUp() {
        testCandidate = new Normaliser();
    }


    @Test
    public void testThatNormaliseWithNullReturnEmptyString() {

        //given
        String jobTitle = null;

        //when
        String result = testCandidate.normalise(jobTitle);

        //then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals("", result);
    }

    @Test
    public void testThatNormaliseWithUnknownJobReturnEmptyString() {

        //given
        String jobTitle = "";

        //when
        String result = testCandidate.normalise(jobTitle);

        //then
        assertNotNull(result);
        assertEquals("", result);
    }

    @Test
    public void testThatNormaliseWithIdealJobReturnIdealJob() {

        //given
        String jobTitle = "Architect";

        //when
        String result = testCandidate.normalise(jobTitle);

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(jobTitle, result);
    }

    @Test
    public void testThatNormaliseWithTestJobsReturnsExpectedNormalizedJobs() {
        //given
        List<String> jobTitles = createJobTitles();
        List<String> normalizedJobTitles = createNormalizedJobTitles();

        //when
        List<String> result = jobTitles.stream()
                .map(jobTitle -> testCandidate.normalise(jobTitle))
                .toList();

        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(normalizedJobTitles, result);
    }

    private List<String> createJobTitles() {
        return List.of(
                "Java engineer",
                "C# engineer",
                "Accountant",
                "Chief Accountant"
        );
    }

    private List<String> createNormalizedJobTitles() {
        return List.of(
                "Software engineer",
                "Software engineer",
                "Accountant",
                "Accountant"
        );
    }
}