package com.java.newscycle.controller;

import com.java.newscycle.entity.Article;
import com.java.newscycle.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class ArticleController {

    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public ResponseEntity<Article> getArticleByID(@RequestParam long id) {

        try {
            Article article = articleService.getArticleByID(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);

        }
    }

    @GetMapping(path = "/article/category")
    public @ResponseBody
    ResponseEntity<List<Article>> getArticlesByCategory(@RequestParam String category) {

        try {
            List<Article> articles = articleService.getArticlesByCategory(category);
            return new ResponseEntity<>(articles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
