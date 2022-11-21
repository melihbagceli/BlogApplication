package com.blogapp.all.controller;

import com.blogapp.all.dto.request.PostRequest;
import com.blogapp.all.dto.response.PostResponse;
import com.blogapp.all.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/")
    private ResponseEntity<PostResponse> createPost(@RequestBody PostRequest postRequest){
        return postService.savePost(postRequest);
    }
}
