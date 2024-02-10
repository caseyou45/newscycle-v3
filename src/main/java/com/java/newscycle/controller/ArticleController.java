package com.java.newscycle.controller;

import com.java.newscycle.entity.Article;
import com.java.newscycle.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/id")
    public ResponseEntity<Article> getArticleByID(@RequestParam long id) {
        try {
            Article article = articleService.getArticleByID(id);
            return ResponseEntity.ok(article);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category")
    public ResponseEntity<List<Article>> getArticlesByCategory(@RequestParam String category) {
        try {
            List<Article> articles = articleService.getArticlesByCategory(category);
            return ResponseEntity.ok(articles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
