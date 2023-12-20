package com.spirngboot.blog.Spring.Payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private Long id;

    // tiltle should not be null or empty
    // title should have at least 2 characters
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // tiltle should not be null or empty
    // title should have at least 10 characters
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String description;

    // tiltle should not be null or empty
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;

    private Long categoryId;
}
