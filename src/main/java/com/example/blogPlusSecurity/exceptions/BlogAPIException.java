package com.example.blogPlusSecurity.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public class BlogAPIException extends RuntimeException{
    private HttpStatus status;
    private String message;
}
