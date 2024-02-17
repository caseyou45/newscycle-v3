package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import com.java.newscycle.exception.NegativeSentimentError;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private SentimentAnalyzerService sentimentAnalyzer;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCommentWithNeutralSentiment() {
        Comment commentRequest = new Comment();
        commentRequest.setContent("neutral");
        commentRequest.setArticle(1L);
        commentRequest.setAuthor(1L);

        when(sentimentAnalyzer.analyzeSentiment("neutral")).thenReturn(0.0);
        when(articleRepository.findById(1L)).thenReturn(java.util.Optional.of(new Article()));
        when(usersRepository.findById(1L)).thenReturn(java.util.Optional.of(new Users()));
        when(commentRepository.save(any(Comment.class))).thenReturn(commentRequest);

        Comment savedComment = commentService.createComment(commentRequest);

        assertEquals(commentRequest, savedComment);
        assertEquals(0.0, savedComment.getSentimentScore());
        verify(commentRepository, times(1)).save(any(Comment.class));
        verify(articleRepository, times(1)).save(any(Article.class));
        verify(usersRepository, times(1)).save(any(Users.class));
    }

    @Test
    void testCreateReply() {
        Comment parentComment = new Comment();
        parentComment.setId(1L);
        parentComment.setReplies(new ArrayList<>());

        Comment commentRequest = new Comment();
        commentRequest.setParentComment(1L);
        commentRequest.setAuthor(1L);

        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> {
            Comment savedComment = invocation.getArgument(0);
            parentComment.getReplies().add(savedComment);
            return savedComment;
        });

        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(parentComment));

        when(usersRepository.findById(1L)).thenReturn(java.util.Optional.of(new Users()));
        Comment savedComment = commentService.createReply(commentRequest);

        assertEquals(commentRequest, savedComment);
        assertEquals(3, parentComment.getReplies().size());
    }

    @Test
    void testUpdateComment() {
        Comment commentRequest = new Comment();
        commentRequest.setId(1L);

        when(commentRepository.save(any(Comment.class))).thenReturn(commentRequest);

        Comment updatedComment = commentService.updateComment(commentRequest);

        assertEquals(commentRequest, updatedComment);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testRemoveComment() {
        Comment commentRequest = new Comment();
        commentRequest.setId(1L);

        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(commentRequest));
        when(commentRepository.save(any(Comment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Comment removedComment = commentService.removeComment(commentRequest);

        assertEquals("COMMENT DELETED", removedComment.getContent());
        assertEquals("[DELETED]", removedComment.getUsername());
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testGetComment() {
        Comment comment = new Comment();
        comment.setId(1L);

        when(commentRepository.findById(1L)).thenReturn(java.util.Optional.of(comment));

        Comment retrievedComment = commentService.getComment(1L);

        assertEquals(comment, retrievedComment);
    }

}
