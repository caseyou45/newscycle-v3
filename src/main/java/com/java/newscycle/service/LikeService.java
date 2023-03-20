package com.java.newscycle.service;


import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikeService {

    CommentRepository commentRepository;

    ArticleRepository articleRepository;

    UsersRepository usersRepository;

    LikeRepository likeRepository;


    @Autowired
    public LikeService(CommentRepository commentRepository, ArticleRepository articleRepository, UsersRepository usersRepository, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
        this.likeRepository = likeRepository;
    }

    public Like createLike(Like like) {


        Comment likedComment = commentRepository.findById(like.getCommentID()).get();

        Like savedLike = likeRepository.save(like);


        likedComment.getLikes().add(savedLike);
        commentRepository.save(likedComment);

        Users user = usersRepository.findById(savedLike.getAuthor()).get();
        user.getLikes().add(savedLike);
        usersRepository.save(user);

        Article article = articleRepository.findById(savedLike.getArticleID()).get();
        article.getLikes().add(savedLike);
        articleRepository.save(article);


        return savedLike;
    }


    public void deleteLike(Like like) {
        Like savedLike = likeRepository.findById(like.getId()).orElseThrow(() -> new NoSuchElementException());

        Article article = articleRepository.findById(savedLike.getArticleID()).orElseThrow(() -> new NoSuchElementException());
        article.getLikes().remove(savedLike);

        Comment comment = commentRepository.findById(savedLike.getCommentID()).orElseThrow(() -> new NoSuchElementException());
        comment.getLikes().remove(savedLike);

        Users user = usersRepository.findById(savedLike.getAuthor()).orElseThrow(() -> new NoSuchElementException());
        user.getLikes().remove(savedLike);

        likeRepository.delete(savedLike);


    }
}
