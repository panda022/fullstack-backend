package com.pangong.fullstackbackendpost.dtos;


import com.pangong.fullstackbackendpost.model.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {
    private Long id;
    private String image;
    private String title;
    private String description;
    private String content;
    private Set<CommentDto> comments;

}
