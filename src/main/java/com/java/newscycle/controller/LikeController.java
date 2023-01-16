package com.java.newscycle.controller;


import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import com.java.newscycle.repository.CommentRepository;
import com.java.newscycle.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class LikeController {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    LikeRepository likeRepository;

    @PostMapping("/like")
    public ResponseEntity<Like> createLike(@RequestParam Long commentID,
                                           @RequestBody Like madeLike) {
        try {


            Comment likedComment = commentRepository.findById(commentID).get();

            madeLike.setComment(likedComment);

            Like savedLike = likeRepository.save(madeLike);

            likedComment.getLikes().add(savedLike);

            commentRepository.save(likedComment);

            return new ResponseEntity<>(savedLike, HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
