package com.java.newscycle.dto.Likes;

import com.java.newscycle.entity.Comment;
import com.java.newscycle.entity.Like;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    Like like;
    Comment comment;
}
