package com.java.newscycle.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.java.newscycle.entity.Comment;

@Component
public class CommentSentimentLogger {

    private static final Logger logger = LoggerFactory.getLogger(CommentSentimentLogger.class);

    public void logNegativeSentimentError(Comment comment) {
        String errorMessage = generateNegativeSentimentErrorMessage(comment);
        logger.error(errorMessage);
    }

    private String generateNegativeSentimentErrorMessage(Comment comment) {
        return "Comment sentiment is too negative. Comment details: "
                + "id=" + comment.getId()
                + ", author=" + comment.getAuthor()
                + ", username=" + comment.getUsername()
                + ", content=" + comment.getContent();
    }
}
