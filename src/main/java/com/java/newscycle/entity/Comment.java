package com.java.newscycle.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    private Long id;
    @Column(name = "author")
    private Long author;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "parentComment")
    private Long parentComment;

    @Column(name = "article")
    private Long article;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "like_comment_fk", referencedColumnName = "id")
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_comment_fk", referencedColumnName = "id")
    private List<Comment> replies;


    public Comment() {
    }

    public Comment(Long author, Boolean deleted, Long parentComment, Long article, String content, List<Like> likes, List<Comment> replies) {
        this.author = author;
        this.deleted = deleted;
        this.parentComment = parentComment;
        this.article = article;
        this.content = content;
        this.likes = likes;
        this.replies = replies;
    }

    public Long getArticle() {
        return article;
    }

    public void setArticle(Long article) {
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getParentComment() {
        return parentComment;
    }

    public void setParentComment(Long parentComment) {
        this.parentComment = parentComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }

    public List<Comment> getReplies() {
        return replies;
    }

    public void setReplies(List<Comment> replies) {
        this.replies = replies;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author=" + author +
                ", deleted=" + deleted +
                ", parentComment=" + parentComment +
                ", article=" + article +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", replies=" + replies +
                '}';
    }
}
