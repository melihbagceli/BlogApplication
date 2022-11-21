package com.blogapp.all.dto.response;

import com.blogapp.all.entity.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PostResponse {
    private String message;
    private Post post;
    private String error;
}
