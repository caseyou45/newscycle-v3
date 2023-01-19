package com.java.newscycle.controller;

import com.java.newscycle.entity.Article;
import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Users;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping(path = "/api")
public class CommentController {


    CommentRepository commentRepository;

    ArticleRepository articleRepository;

    UsersRepository usersRepository;


    @Autowired
    public CommentController(CommentRepository commentRepository, ArticleRepository articleRepository, UsersRepository usersRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
        this.usersRepository = usersRepository;
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment commentRequest) {
        try {

            Article referencedArticle = articleRepository.findById(commentRequest.getArticle()).get();

            System.out.println(commentRequest);
            Comment savedComment = commentRepository.save(commentRequest);

            referencedArticle.getComments().add(savedComment);
            articleRepository.save(referencedArticle);

            Users user = usersRepository.findById(commentRequest.getAuthor()).get();

            user.getComments().add(savedComment);

            usersRepository.save(user);

            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comment/reply")
    public ResponseEntity<Comment> createReply(@RequestBody Comment commentRequest) {
        try {


            Comment parentComment = commentRepository.findById(commentRequest.getParentComment()).get();

            Comment savedComment = commentRepository.save(commentRequest);

            parentComment.getReplies().add(savedComment);

            commentRepository.save(parentComment);

            Users user = usersRepository.findById(commentRequest.getAuthor()).get();

            user.getComments().add(savedComment);

            usersRepository.save(user);

            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/comment")
    public @ResponseBody
    ResponseEntity<Comment> updateCommentByID(@RequestBody Comment comment) {
        try {
            Comment savedComment = commentRepository.save(comment);
            return new ResponseEntity<>(savedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/comment")
    public ResponseEntity<Comment> removeComment(@RequestBody Comment comment) {
        try {
            Comment oldComment = commentRepository.findById(comment.getId()).get();
            oldComment.setContent("COMMENT DELETED");
            oldComment.setDeleted(true);
            commentRepository.save(oldComment);
            return new ResponseEntity<>(oldComment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/comment")
    public ResponseEntity<Comment> getComment(@RequestParam Long commentID) {
        try {
            Comment comment = commentRepository.findById(commentID).get();
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

    }
}
