package com.java.newscycle.service;

import com.java.newscycle.dto.Comment.CommentDTO;
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

    public Comment createComment(CommentDTO commentRequest) {
        Comment comment = mapToEntity(commentRequest);

        Article referencedArticle = articleRepository.findById(commentRequest.getArticle()).get();

        Users user = usersRepository.findById(commentRequest.getAuthor()).get();

        comment.setUsername(user.getUsername());

        Comment savedComment = commentRepository.save(comment);

        referencedArticle.getComments().add(savedComment);

        articleRepository.save(referencedArticle);

        user.getComments().add(savedComment);

        usersRepository.save(user);

        return savedComment;
    }

    public Comment createReply(CommentDTO commentRequest) {
        Comment comment = mapToEntity(commentRequest);

        Comment parentComment = commentRepository.findById(commentRequest.getParentComment()).orElseThrow(() -> new NoSuchElementException());

        Comment savedComment = commentRepository.save(comment);

        parentComment.getReplies().add(savedComment);

        commentRepository.save(parentComment);

        Users user = usersRepository.findById(commentRequest.getAuthor()).orElseThrow(() -> new NoSuchElementException());

        user.getComments().add(savedComment);

        usersRepository.save(user);

        return savedComment;
    }

    public Comment updateComment(CommentDTO commentRequest) {
        Comment comment = mapToEntity(commentRequest);
        return commentRepository.save(comment);
    }

    public Comment removeComment(CommentDTO commentRequest) {
        Comment comment = mapToEntity(commentRequest);
        Comment oldComment = commentRepository.findById(comment.getId()).orElseThrow(() -> new NoSuchElementException());
        oldComment.setContent("COMMENT DELETED");
        oldComment.setDeleted(true);
        return commentRepository.save(oldComment);
    }

    public Comment getComment(Long commentID) {
        return commentRepository.findById(commentID).orElseThrow(() -> new NoSuchElementException());
    }


    private Comment mapToEntity(CommentDTO commentRequest) {
        Comment comment = new Comment();
        comment.setAuthor(commentRequest.getAuthor());
        comment.setDeleted(commentRequest.getDeleted());
        comment.setParentComment(commentRequest.getAuthor());
        comment.setArticle(commentRequest.getArticle());
        comment.setContent(commentRequest.getContent());
        comment.setDate(commentRequest.getDate());
        return comment;
    }
}
