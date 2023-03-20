package com.java.newscycle.service;

import com.java.newscycle.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    private Comment savedComment;

    private Comment commentRequest;

    private Comment replyComment;


    @BeforeEach
    public void setup() {

        commentRequest = new Comment();
        commentRequest.setAuthor(10L);
        commentRequest.setArticle(1L);
        commentRequest.setContent("TEST CONTENT");
        commentRequest.setDate(new java.util.Date());
        commentRequest.setUsername("test");


    }


    @Test
    void createComment() {


        savedComment = commentService.createComment(commentRequest);

        assertThat(savedComment.getContent()).as("Save New Comment").isEqualTo(commentRequest.getContent());

    }

    @Test
    void createReply() {

        savedComment = commentService.createComment(commentRequest);

        commentRequest.setParentComment(savedComment.getId());

        replyComment = commentService.createComment(commentRequest);

        assertThat(replyComment.getParentComment()).as("Save New Reply").isEqualTo(commentRequest.getParentComment());
    }

    @Test
    void updateComment() {
        savedComment = commentService.createComment(commentRequest);

        String newContent = "New Content";

        commentRequest.setContent(newContent);

        Comment updatedComment = commentService.updateComment(commentRequest);

        assertThat(updatedComment.getParentComment()).as("Update Comment Content").isEqualTo(commentRequest.getParentComment());


    }

    @Test
    void removeComment() {
        savedComment = commentService.createComment(commentRequest);
        commentRequest.setId(savedComment.getId());
        Comment removedComment = commentService.removeComment(commentRequest);
        assertThat(removedComment.getUsername()).as("Remove Comment").isEqualTo("[DELETED]");

    }

    @Test
    void getComment() {
        savedComment = commentService.createComment(commentRequest);
        Comment comment = commentService.getComment(savedComment.getId());
        assertThat(savedComment.getId()).as("Get Comment").isEqualTo(comment.getId());

    }
}