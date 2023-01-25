package com.java.newscycle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "like_generator")
    private Long id;

    @Column(name = "author")
    private Long author;

    @Column(name = "articleID")
    private Long articleID;
    @Column(name = "commentID")
    private Long commentID;


}