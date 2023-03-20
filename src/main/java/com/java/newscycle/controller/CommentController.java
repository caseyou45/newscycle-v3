package com.java.newscycle.controller;

import com.java.newscycle.entity.Comment;
import com.java.newscycle.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;


@RestController
@RequestMapping(path = "/api")
public class CommentController {

    CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment")
    public ResponseEntity<Comment> createComment(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.createComment(commentRequest);
            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/comment/reply")
    public ResponseEntity<Comment> createReply(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.createReply(commentRequest);
            return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping(path = "/comment")
    public @ResponseBody
    ResponseEntity<Comment> updateComment(@RequestBody Comment commentRequest) {
        try {
            Comment savedComment = commentService.updateComment(commentRequest);
            return new ResponseEntity<>(savedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PatchMapping("/comment/delete")
    public ResponseEntity<Comment> removeComment(@RequestBody Comment commentRequest) {
        try {
            Comment oldComment = commentService.removeComment(commentRequest);
            return new ResponseEntity<>(oldComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @GetMapping("/comment")
    public ResponseEntity<Comment> getComment(@RequestParam Long commentID) {
        try {
            Comment comment = commentService.getComment(commentID);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }


}
