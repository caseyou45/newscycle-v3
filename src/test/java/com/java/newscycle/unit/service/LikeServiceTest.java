package com.java.newscycle.unit.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import com.java.newscycle.repository.UsersRepository;
import com.java.newscycle.service.LikeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @InjectMocks
    LikeService likeService;

    @Mock
    CommentRepository commentRepository;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    UsersRepository usersRepository;

    @Mock
    LikeRepository likeRepository;

    Comment comment;
    Article article;
    Users user;
    Like madeLike;

    @BeforeEach
    void setUp() {
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        user = new Users(1L, "username", "password", sqlDate, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ArrayList<>());
        // comment = new Comment(1L, 1L, "username", false, null, 1L, sqlDate,
        // "testContent", new ArrayList<>(), new ArrayList<>());
        article = new Article(1L, "title", "description", "author", "General", "content", "source_id", "source_name",
                "publishedat", "url", "urltoimage", new ArrayList<>(), new ArrayList<>());
        madeLike = new Like(1L, 1L, 1L, 1L);

    }

    @Test
    void createLike() {

        when(commentRepository.findById(madeLike.getCommentID())).thenReturn(Optional.of(comment));

        when(usersRepository.findById(madeLike.getAuthor())).thenReturn(Optional.of(user));

        when(articleRepository.findById(madeLike.getArticleID())).thenReturn(Optional.of(article));

        when(likeRepository.save(madeLike)).thenReturn(madeLike);

        Like savedLike = likeService.createLike(madeLike);

        assertThat(savedLike).isEqualTo(madeLike);

    }

    @Test
    void deleteLike() {

        when(commentRepository.findById(madeLike.getCommentID())).thenReturn(Optional.of(comment));

        when(usersRepository.findById(madeLike.getAuthor())).thenReturn(Optional.of(user));

        when(articleRepository.findById(madeLike.getArticleID())).thenReturn(Optional.of(article));

        when(likeRepository.findById(madeLike.getArticleID())).thenReturn(Optional.of(madeLike));

        likeService.deleteLike(madeLike);

    }
}