package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import com.java.newscycle.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class LikeServiceTest {

    @Mock
    CommentRepository commentRepository;

    @Mock
    ArticleRepository articleRepository;

    @Mock
    UsersRepository usersRepository;

    @Mock
    LikeRepository likeRepository;


    LikeService likeService;

    @BeforeEach
    void setUp() {
        likeService = new LikeService(commentRepository, articleRepository, usersRepository, likeRepository);
    }


    @Test
    void createLike() {

        Like like = new Like();
        like.setAuthor(1L);
        like.setArticleID(2L);
        like.setCommentID(3L);


        Comment comment = new Comment();
        comment.setId(3L);
        comment.setAuthor(5L);
        comment.setArticle(2L);
        comment.setContent("TEST CONTENT");
        comment.setDate(new java.util.Date());
        comment.setUsername("TEST USER");
        comment.setLikes(new ArrayList<>());


        Users user = new Users();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("testpassword");
        user.setLikes(new ArrayList<>());

        Article article = new Article();
        article.setId(2L);
        article.setLikes(new ArrayList<>());

        when(commentRepository.findById(3L)).thenReturn(Optional.of(comment));

        Comment likedComment = commentRepository.findById(like.getCommentID()).get();


        when(likeRepository.save(like)).thenReturn(like);


        Like savedLike = likeRepository.save(like);
        likedComment.getLikes().add(savedLike);
        commentRepository.save(likedComment);

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));


        Users u = usersRepository.findById(savedLike.getAuthor()).get();
        u.getLikes().add(savedLike);
        usersRepository.save(u);


        when(articleRepository.findById(2L)).thenReturn(Optional.of(article));

        Article a = articleRepository.findById(savedLike.getArticleID()).get();
        a.getLikes().add(savedLike);
        articleRepository.save(a);

    }

    @Test
    void deleteLike() {
    }
}