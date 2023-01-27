package com.java.newscycle.service;

import com.java.newscycle.dto.Comment.CommentDTO;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Autowired
    UsersService usersService;

    @Autowired
    ArticleService articleService;


    CommentDTO commentDTO;

    Users user;


    @BeforeEach
    public void setup() {

        commentDTO = new CommentDTO();
        commentDTO.setAuthor(1L);
        commentDTO.setDeleted(false);
        commentDTO.setParentComment(null);
        commentDTO.setArticle(1L);
        commentDTO.setContent("TEST");
        commentDTO.setDate(new Date());

    }


    @Test
    void createComment() {
        Comment savedComment = commentService.createComment(commentDTO);
        assertNotNull(savedComment);

    }


    @Test
    void createReply() {
        commentDTO.setParentComment(1L);
        Comment savedReply = commentService.createComment(commentDTO);
        assertNotNull(savedReply);
    }

    @Test
    void updateComment() {
        Comment savedComment = commentService.createComment(commentDTO);


        CommentDTO u = new CommentDTO(savedComment.getId(),
                savedComment.getAuthor(),
                savedComment.getDeleted(),
                savedComment.getParentComment(),
                savedComment.getArticle(),
                "REDO",
                savedComment.getDate());


        Comment updatedComment = commentService.updateComment(u);
        assertEquals(updatedComment.getContent(), "REDO");


    }

    @Test
    void removeComment() {
        Comment savedComment = commentService.createComment(commentDTO);
        commentDTO.setId(savedComment.getId());
        Comment deletedComment = commentService.removeComment(commentDTO);
        assertEquals(deletedComment.getDeleted(), true);
        assertEquals(deletedComment.getContent(), "COMMENT DELETED");
    }

    @Test
    void getComment() {
        Comment savedComment = commentService.createComment(commentDTO);
        assertNotNull(commentService.getComment(savedComment.getId()));
    }
}