package com.spirngboot.blog.Spring.Payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@Schema(
        description = "PostDto Model Information"
)
public class PostDto {

    private Long id;

    // tiltle should not be null or empty
    // title should have at least 2 characters
    @Schema(
            description = "Blog post title"
    )
    @NotEmpty
    @Size(min = 2, message = "Post title should have at least 2 characters")
    private String title;

    // tiltle should not be null or empty
    // title should have at least 10 characters
    @Schema(
            description = "Blog post description"
    )
    @NotEmpty
    @Size(min = 10, message = "Post title should have at least 10 characters")
    private String description;

    // tiltle should not be null or empty
    @Schema(
            description = "Blog post content"
    )
    @NotEmpty
    private String content;
    private Set<CommentDto> comments;
    @Schema(
            description = "Blog post category"
    )
    private Long categoryId;
}
