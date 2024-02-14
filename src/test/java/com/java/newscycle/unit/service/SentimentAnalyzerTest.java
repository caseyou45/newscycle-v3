package com.java.newscycle.unit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.java.newscycle.service.SentimentAnalyzerService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SentimentAnalyzerTest {

    @Autowired
    private SentimentAnalyzerService sentimentAnalyzer;

    @Test
    void testAnalyzeSentiment_VeryNegative() {
        String commentText = "Your ongoing disregard for the suffering of innocent animals is sickening. It's a shameful display of heartlessness and indifference. We deserve leaders who show compassion, not callousness.";
        double result = sentimentAnalyzer.analyzeSentiment(commentText);
        assertEquals(-2.0, result);
    }

    @Test
    void testAnalyzeSentiment_Negative() {
        String commentText = "This article is terrible!";
        double result = sentimentAnalyzer.analyzeSentiment(commentText);
        assertEquals(-1.0, result);
    }

    @Test
    void testAnalyzeSentiment_Neutral() {
        String commentText = "This article is okay. Meh. Whatever.";
        double result = sentimentAnalyzer.analyzeSentiment(commentText);
        assertEquals(0.0, result);
    }

    @Test
    void testAnalyzeSentiment_Positive() {
        String commentText = "I love this article!";
        double result = sentimentAnalyzer.analyzeSentiment(commentText);
        assertEquals(1.0, result);
    }

    @Test
    void testAnalyzeSentiment_VeryPositive() {
        String commentText = "This article is amazing!";
        double result = sentimentAnalyzer.analyzeSentiment(commentText);
        assertEquals(2.0, result);
    }

}
