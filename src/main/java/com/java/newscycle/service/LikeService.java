package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LikeService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UsersRepository usersRepository;
    private final LikeRepository likeRepository;

    public LikeService(CommentRepository commentRepository, ArticleRepository articleRepository,
            UsersRepository usersRepository, LikeRepository likeRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
        this.likeRepository = likeRepository;
    }

    public Like createLike(Like like) {
        Comment likedComment = getCommentById(like.getCommentID());
        Article article = getArticleById(like.getArticleID());
        Users user = getUserById(like.getAuthor());

        Like savedLike = likeRepository.save(like);

        likedComment.getLikes().add(savedLike);
        commentRepository.save(likedComment);

        user.getLikes().add(savedLike);
        usersRepository.save(user);

        article.getLikes().add(savedLike);
        articleRepository.save(article);

        return savedLike;
    }

    public void deleteLike(Like like) {
        Like savedLike = likeRepository.findById(like.getId())
                .orElseThrow(() -> new NoSuchElementException("Like not found with ID: " + like.getId()));

        Article article = getArticleById(savedLike.getArticleID());
        article.getLikes().remove(savedLike);
        articleRepository.save(article);

        Comment comment = getCommentById(savedLike.getCommentID());
        comment.getLikes().remove(savedLike);
        commentRepository.save(comment);

        Users user = getUserById(savedLike.getAuthor());
        user.getLikes().remove(savedLike);
        usersRepository.save(user);

        likeRepository.delete(savedLike);
    }

    private Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with ID: " + articleId));
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with ID: " + commentId));
    }

    private Users getUserById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }
}
