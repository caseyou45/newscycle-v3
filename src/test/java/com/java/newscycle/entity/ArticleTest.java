package com.java.newscycle.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ArticleTest {

    @Test
    public void testArticleEntity() {
        // Arrange
        String title = "Test Article";
        String description = "This is a test article";
        String author = "Test Author";
        String category = "Test Category";
        String content = "Test content";
        String sourceId = "Test Source ID";
        String sourceName = "Test Source Name";
        String publishedAt = "2024-02-16";
        String url = "http://example.com/article";
        String urlToImage = "http://example.com/image.jpg";
        List<Comment> comments = new ArrayList<>();
        List<Like> likes = new ArrayList<>();

        // Act
        Article article = new Article(title, description, author, category, content, sourceId, sourceName,
                publishedAt, url, urlToImage, comments, likes);

        // Assert
        assertNotNull(article);
        assertEquals(title, article.getTitle());
        assertEquals(description, article.getDescription());
        assertEquals(author, article.getAuthor());
        assertEquals(category, article.getCategory());
        assertEquals(content, article.getContent());
        assertEquals(sourceId, article.getSource_id());
        assertEquals(sourceName, article.getSource_name());
        assertEquals(publishedAt, article.getPublishedat());
        assertEquals(url, article.getUrl());
        assertEquals(urlToImage, article.getUrltoimage());
        assertEquals(comments, article.getComments());
        assertEquals(likes, article.getLikes());
    }
}
