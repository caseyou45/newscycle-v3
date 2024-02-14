package com.java.newscycle.controller;

import com.java.newscycle.entity.Comment;
import com.java.newscycle.exception.NegativeSentimentError;
import com.java.newscycle.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.createComment(commentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        } catch (NegativeSentimentError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/reply")
    public ResponseEntity<Comment> createReply(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.createReply(commentRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedComment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<Comment> updateComment(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.updateComment(commentRequest);
            return ResponseEntity.ok(savedComment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/delete")
    public ResponseEntity<Comment> removeComment(@RequestBody Comment commentRequest) {
        try {
            Comment oldComment = commentService.removeComment(commentRequest);
            return ResponseEntity.ok(oldComment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{commentID}")
    public ResponseEntity<Comment> getComment(@PathVariable Long commentID) {
        try {
            Comment comment = commentService.getComment(commentID);
            return ResponseEntity.ok(comment);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
