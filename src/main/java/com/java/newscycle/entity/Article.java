package com.java.newscycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_generator")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "category")
    private String category;
    @Column(name = "content")
    private String content;
    @Column(name = "source_id")
    private String source_id;
    @Column(name = "source_name")
    private String source_name;
    @Column(name = "publishedat")
    private String publishedat;
    @Column(name = "url", columnDefinition = "TEXT")
    private String url;
    @Column(name = "urltoimage", columnDefinition = "TEXT")
    private String urltoimage;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_article_fk", referencedColumnName = "id")
    private List<Comment> comments = List.of();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "like_article_fk", referencedColumnName = "id")
    private List<Like> likes = List.of();

    public Article(String title, String description, String author, String category, String content, String source_id,
            String source_name, String publishedat, String url, String urltoimage, List<Comment> comments,
            List<Like> likes) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.category = category;
        this.content = content;
        this.source_id = source_id;
        this.source_name = source_name;
        this.publishedat = publishedat;
        this.url = url;
        this.urltoimage = urltoimage;
        this.comments = comments;
        this.likes = likes;
    }
}
