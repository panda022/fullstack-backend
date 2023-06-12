package com.pangong.fullstackbackendpost.dtos;


import com.pangong.fullstackbackendpost.model.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto modal information"
)
public class PostDto {
    private Long id;

    //image should not be null or empty
    @NotEmpty(message = "not be null or empty")
    //image should be at least 2 charcters
    @Size(min = 2,message = "image should be at least 2 characters")
    private String image;

    //title should not be null or empty
    @Schema(
            description = "Blog post title"
    )
    @NotEmpty(message = "not be null or empty")
    //title should be at least 2 charcters
    @Size(min = 2,message = "title should be at least 2 characters")
    private String title;
    //description should not be null or empty
    @Schema(
            description = "Blog post description"
    )
    @NotEmpty(message = "not be null or empty")
    private String description;
    //content should not be null or empty
    @Schema(
            description = "Blog post content"
    )
    @NotEmpty(message = "not be null or empty")
    private String content;
    private Set<CommentDto> comments;

}
