package com.java.newscycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_generator")
    private Long id;

    @Column(name = "author")
    private Long author;

    @Column(name = "username")
    private String username;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "parentComment")
    private Long parentComment;

    @Column(name = "article")
    private Long article;

    @Column(name = "date")
    private Date date = new Date();

    @Column(name = "content", columnDefinition = "TEXT")
    private String content = "";

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "like_comment_fk", referencedColumnName = "id")
    private List<Like> likes = List.of();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_comment_fk", referencedColumnName = "id")
    private List<Comment> replies = List.of();
}
