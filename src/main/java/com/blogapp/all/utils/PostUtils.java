package com.blogapp.all.utils;

import com.blogapp.all.dto.request.PostRequest;
import com.blogapp.all.dto.response.PostResponse;
import com.blogapp.all.entity.Post;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PostUtils {
    public static Post postRequestToPost(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setMetaTitle(postRequest.getMetaTitle());
        post.setSlug(postRequest.getSlug());
        post.setSummary(postRequest.getSummary());
        post.setPublished(postRequest.getPublished());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(postRequest.getPublishedAt(), formatter);
        post.setPublishedAt(dateTime);
        post.setContent(postRequest.getContent());
        post.setAuthor(postRequest.getAuthor());
        return post;
    }
    public static PostResponse postToPostResponse(Post postDb, String message){
        PostResponse postResponse = new PostResponse();
        postResponse.setPost(postDb);
        postResponse.setMessage(message);
        return postResponse;
    }
}
