package com.example.blogPlusSecurity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    @JsonProperty(access = WRITE_ONLY)
    private String password;
}
