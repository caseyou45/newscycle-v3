package com.java.newscycle.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private Long author;
    private Boolean deleted;
    private Long parentComment;
    private Long article;
    private String content;
    private Date date;


}
