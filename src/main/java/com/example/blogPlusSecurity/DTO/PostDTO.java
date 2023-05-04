package com.example.blogPlusSecurity.DTO;

import com.example.blogPlusSecurity.entity.User;
import com.example.blogPlusSecurity.request.RegisterRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

@Data
public class PostDTO {
    private Long id;

    @NotBlank
    @Size(min = 2, message = "Post title should have at least two characters")
    private String title;

    @NotBlank
    @Size(min = 10, message = "Description should have at least 10 characters")
    private String description;

    @NotBlank
    private String content;

    private RegisterRequest user;

    private Set<CommentDTO> comments;

}
