package com.java.newscycle.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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
    @Column(name = "url")
    private String url;
    @Column(name = "urltoimage", columnDefinition = "TEXT")
    private String urltoimage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_article_fk", referencedColumnName = "id")
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_article_fk", referencedColumnName = "id")
    private List<Like> likes;


    public Article() {

    }

    public Article(String title, String description, String author, String category, String content, String source_id, String source_name, String publishedat, String url, String urltoimage, ArrayList<Comment> comments, ArrayList<Like> likes) {
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

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public String getPublishedat() {
        return publishedat;
    }

    public void setPublishedat(String publishedat) {
        this.publishedat = publishedat;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrltoimage() {
        return urltoimage;
    }

    public void setUrltoimage(String urltoimage) {
        this.urltoimage = urltoimage;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
