package com.java.newscycle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CommentTest {

    @Test
    public void testCommentEntity() {
        // Arrange
        Long id = 1L;
        Long author = 123L;
        String username = "test_user";
        Boolean deleted = false;
        Long parentComment = null;
        Long article = 456L;
        Date date = new Date();
        String content = "Test comment content";
        double sentimentScore = 0.0;
        List<Like> likes = new ArrayList<>();
        List<Comment> replies = new ArrayList<>();

        // Act
        Comment comment = new Comment(id, author, username, deleted, parentComment, article, date, content,
                sentimentScore,
                likes, replies);

        // Assert
        assertNotNull(comment);
        assertEquals(author, comment.getAuthor());
        assertEquals(username, comment.getUsername());
        assertEquals(deleted, comment.getDeleted());
        assertEquals(parentComment, comment.getParentComment());
        assertEquals(article, comment.getArticle());
        assertEquals(date, comment.getDate());
        assertEquals(content, comment.getContent());
        assertEquals(sentimentScore, comment.getSentimentScore());
        assertEquals(likes, comment.getLikes());
        assertEquals(replies, comment.getReplies());
    }
}
