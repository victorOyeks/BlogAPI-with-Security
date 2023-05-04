package com.example.blogPlusSecurity.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class CommentDTO {
    private Long id;

    @NotEmpty(message = "Name should not be null or empty")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    @Email
    private String email;

    @Size(min = 10, message = "Comment body should be 10 min characters")
    private String body;
}
