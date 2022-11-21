package com.blogapp.all.dto.request;

import com.blogapp.all.entity.Post;
import com.blogapp.all.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties
@Data
@Getter
@Setter
public class PostRequest {
    @NotEmpty
    private String title;
    private String metaTitle;
    private String slug;
    private String summary;
    @JsonFormat(pattern="yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String publishedAt;
    private Boolean published;
    private String content;
    private User author;
    private int parentPostId;
}
