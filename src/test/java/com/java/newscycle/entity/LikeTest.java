package com.java.newscycle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class LikeTest {

    @Test
    public void testLikeEntityConstructor() {
        // Arrange
        Long id = 1L;
        Long author = 100L;
        Long articleID = 200L;
        Long commentID = 300L;

        // Act
        Like like = new Like(id, author, articleID, commentID);

        // Assert
        assertNotNull(like);
        assertEquals(id, like.getId());
        assertEquals(author, like.getAuthor());
        assertEquals(articleID, like.getArticleID());
        assertEquals(commentID, like.getCommentID());
    }
}
