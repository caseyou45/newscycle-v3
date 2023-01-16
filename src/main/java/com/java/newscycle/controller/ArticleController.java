package com.java.newscycle.controller;

import com.java.newscycle.entity.Article;
import com.java.newscycle.repository.ArticleRepository;
import com.java.newscycle.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ArticleController {

    ArticleRepository articleRepository;
    ArticleService articleService;

    @Autowired
    public ArticleController(ArticleRepository articleRepository, ArticleService articleService) {
        this.articleRepository = articleRepository;
        this.articleService = articleService;
    }

    @GetMapping("/article")
    public ResponseEntity<Article> getArticleByID(@RequestParam long id) {
        Article article = articleRepository.findById(id).get();
        return new ResponseEntity<>(article, HttpStatus.OK);
    }

    @GetMapping(path = "/article/category")
    public @ResponseBody
    List<Article> getArticlesByCategory(@RequestParam String category) throws ParseException {
        return articleService.getArticlesByCategory(category);
    }
}
