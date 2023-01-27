package com.java.newscycle.controller;


import com.java.newscycle.entity.Like;
import com.java.newscycle.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class LikeController {

    LikeService likeService;


    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/like")
    public ResponseEntity<Like> createLike(@RequestBody Like madeLike) {
        try {
            Like savedLike = likeService.createLike(madeLike);
            return new ResponseEntity<>(savedLike, HttpStatus.OK);

        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/like")
    public ResponseEntity<Like> deleteLike(@RequestBody Like like) {
        try {
            likeService.deleteLike(like);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
