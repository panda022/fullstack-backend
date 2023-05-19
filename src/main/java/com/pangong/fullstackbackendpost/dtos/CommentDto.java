package com.pangong.fullstackbackendpost.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CommentDto {
    private long id;

    @NotEmpty(message = "not be null or empty")
    private String name;

    @NotEmpty(message = "not be null or empty")
    @Email(message = "invalid email address")
    private String email;

    @NotEmpty(message = "not be null or empty")
    @Size(min=10,message = "body should be at least 10 characters")
    private String body;
}
