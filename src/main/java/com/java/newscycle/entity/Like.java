package com.java.newscycle.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_generator")
    private Long id;

    @Column(name = "deleted")
    private Boolean deleted;

    @Column(name = "author")
    private Integer author;

    @Column(name = "commentID")
    private Long commentID;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "comment_like_id", nullable = false)
    private Comment comment;

    public Like() {
    }

    public Like(Boolean deleted, Integer author) {
        this.deleted = deleted;
        this.author = author;
    }

    public Like(Boolean deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}