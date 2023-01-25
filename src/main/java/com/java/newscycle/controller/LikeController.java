package com.java.newscycle.controller;


import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class LikeController {

    CommentRepository commentRepository;

    ArticleRepository articleRepository;

    UsersRepository usersRepository;

    LikeRepository likeRepository;

    public LikeController(CommentRepository commentRepository, ArticleRepository articleRepository, UsersRepository usersRepository, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
        this.likeRepository = likeRepository;
    }

    @PostMapping("/like")
    public ResponseEntity<Like> createLike(@RequestBody Like madeLike) {
        try {
            Comment likedComment = commentRepository.findById(madeLike.getCommentID()).get();

            Like savedLike = likeRepository.save(madeLike);

            likedComment.getLikes().add(savedLike);
            commentRepository.save(likedComment);

            Users user = usersRepository.findById(savedLike.getAuthor()).get();
            user.getLikes().add(savedLike);
            usersRepository.save(user);

            Article article = articleRepository.findById(savedLike.getArticleID()).get();
            article.getLikes().add(savedLike);
            articleRepository.save(article);

            return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/like")
    public ResponseEntity<Like> deleteLike(@RequestBody Like like) {
        try {

            Like savedLike = likeRepository.findById(like.getId()).get();

            Article article = articleRepository.findById(savedLike.getArticleID()).get();
            article.getLikes().remove(savedLike);

            Comment comment = commentRepository.findById(savedLike.getCommentID()).get();
            comment.getLikes().remove(savedLike);

            Users user = usersRepository.findById(savedLike.getAuthor()).get();
            user.getLikes().remove(savedLike);

            likeRepository.delete(savedLike);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
