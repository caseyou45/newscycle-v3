package com.java.newscycle.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NegativeSentimentErrorTest {

    @Test
    public void testNegativeSentimentError() {
        // Arrange
        String errorMessage = "Negative sentiment detected!";

        // Act
        NegativeSentimentError error = new NegativeSentimentError(errorMessage);

        // Assert
        assertEquals(errorMessage, error.getMessage());
    }
}
