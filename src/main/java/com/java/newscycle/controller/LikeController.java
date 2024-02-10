package com.java.newscycle.controller;

import com.java.newscycle.entity.Like;
import com.java.newscycle.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Like> createLike(@RequestBody Like like) {
        try {
            Like savedLike = likeService.createLike(like);
            return ResponseEntity.ok(savedLike);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping
    public ResponseEntity<Void> deleteLike(@RequestBody Like like) {
        try {
            likeService.deleteLike(like);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
