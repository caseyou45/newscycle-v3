package com.java.newscycle.repository;

import com.java.newscycle.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticleByUrltoimage(String urltoimage);

    List<Article> getArticlesByCategoryOrderByPublishedatDesc(String category);
}
