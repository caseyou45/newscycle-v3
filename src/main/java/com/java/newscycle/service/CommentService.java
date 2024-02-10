package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final UsersRepository usersRepository;

    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository,
            UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    public Comment createComment(Comment commentRequest) {
        Article referencedArticle = getArticleById(commentRequest.getArticle());
        Users user = getUserById(commentRequest.getAuthor());

        Comment savedComment = commentRepository.save(commentRequest);
        referencedArticle.getComments().add(savedComment);
        articleRepository.save(referencedArticle);

        user.getComments().add(savedComment);
        usersRepository.save(user);

        return savedComment;
    }

    public Comment createReply(Comment commentRequest) {
        Comment parentComment = getCommentById(commentRequest.getParentComment());
        Comment savedComment = commentRepository.save(commentRequest);

        parentComment.getReplies().add(savedComment);
        commentRepository.save(parentComment);

        Users user = getUserById(commentRequest.getAuthor());
        user.getComments().add(savedComment);
        usersRepository.save(user);

        return savedComment;
    }

    public Comment updateComment(Comment commentRequest) {
        return commentRepository.save(commentRequest);
    }

    public Comment removeComment(Comment commentRequest) {
        Comment oldComment = getCommentById(commentRequest.getId());
        oldComment.setContent("COMMENT DELETED");
        oldComment.setDeleted(true);
        oldComment.setUsername("[DELETED]");
        return commentRepository.save(oldComment);
    }

    public Comment getComment(Long commentID) {
        return getCommentById(commentID);
    }

    private Article getArticleById(Long articleId) {
        return articleRepository.findById(articleId)
                .orElseThrow(() -> new NoSuchElementException("Article not found with ID: " + articleId));
    }

    private Users getUserById(Long userId) {
        return usersRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new NoSuchElementException("Comment not found with ID: " + commentId));
    }
}
