package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;


@Service
public class CommentService {

    CommentRepository commentRepository;

    ArticleRepository articleRepository;

    UsersRepository usersRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, ArticleRepository articleRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    public Comment createComment(Comment commentRequest) {

        Article referencedArticle = articleRepository.findById(commentRequest.getArticle()).get();

        Users user = usersRepository.findById(commentRequest.getAuthor()).get();

        Comment savedComment = commentRepository.save(commentRequest);

        referencedArticle.getComments().add(savedComment);

        articleRepository.save(referencedArticle);

        user.getComments().add(savedComment);

        usersRepository.save(user);

        return savedComment;
    }

    public Comment createReply(Comment commentRequest) {

        Comment parentComment = commentRepository.findById(commentRequest.getParentComment()).orElseThrow(() -> new NoSuchElementException());

        Comment savedComment = commentRepository.save(commentRequest);

        parentComment.getReplies().add(savedComment);

        commentRepository.save(parentComment);

        Users user = usersRepository.findById(commentRequest.getAuthor()).orElseThrow(() -> new NoSuchElementException());

        user.getComments().add(savedComment);

        usersRepository.save(user);

        return savedComment;
    }

    public Comment updateComment(Comment commentRequest) {
        return commentRepository.save(commentRequest);
    }

    public Comment removeComment(Comment commentRequest) {
        Comment oldComment = commentRepository.findById(commentRequest.getId()).orElseThrow(() -> new NoSuchElementException());
        oldComment.setContent("COMMENT DELETED");
        oldComment.setDeleted(true);
        oldComment.setUsername("[DELETED]");
        return commentRepository.save(oldComment);
    }

    public Comment getComment(Long commentID) {
        return commentRepository.findById(commentID).orElseThrow(() -> new NoSuchElementException());
    }


}
