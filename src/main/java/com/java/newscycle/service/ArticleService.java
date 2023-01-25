package com.java.newscycle.service;

import com.java.newscycle.entity.Article;
import com.java.newscycle.repository.ArticleRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticlesByCategory(String category) {
        String uri = String.format("https://newsapi.org/v2/top-headlines?country=us&category=%s&apiKey=f8453aaefcaf4cbf90fe82afa03b2bc1", category);

        //API in to String
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        JSONObject jsnobject = new JSONObject(result);


        JSONArray articleObjectArray = jsnobject.getJSONArray("articles");

        for (int i = 0; i < articleObjectArray.length(); i++) {

            JSONObject articleObject = articleObjectArray.getJSONObject(i);
            String author = "";
            String content = "";
            String description = "";
            String source_id = "";
            String source_name = "";
            String publishedat = "";
            String title = "";
            String url = "";
            String urltoimage = "";

            if (articleObject.get("author") != null) author = String.valueOf(articleObject.get("author"));
            if (articleObject.get("content") != null) content = String.valueOf(articleObject.get("content"));
            if (articleObject.get("description") != null)
                description = String.valueOf(articleObject.get("description"));
            if (articleObject.getJSONObject("source").get("id") != null)
                source_id = String.valueOf(articleObject.getJSONObject("source").get("id"));
            if (articleObject.getJSONObject("source").get("name") != null)
                source_name = String.valueOf(articleObject.getJSONObject("source").get("name"));

            if (articleObject.get("publishedAt") != null)
                publishedat = String.valueOf(articleObject.get("publishedAt"));
            if (articleObject.get("title") != null) title = String.valueOf(articleObject.get("title"));
            if (articleObject.get("url") != null) url = String.valueOf(articleObject.get("url"));
            if (articleObject.get("urlToImage") != null) urltoimage = String.valueOf(articleObject.get("urlToImage"));

            if (articleRepository.findArticleByUrltoimage(urltoimage).size() == 0) {
                articleRepository.save(new Article(title, description, author, category, content, source_id, source_name, publishedat, url, urltoimage, new ArrayList<>(), new ArrayList<>()));
            }

        }

        return articleRepository.getArticlesByCategoryOrderByPublishedatDesc(category);
    }

    public Article getArticleByID(Long articleID) {
        return articleRepository.findById(articleID).orElseThrow(() -> new NoSuchElementException("Can Not Find Article With Provided ID"));

    }

}
