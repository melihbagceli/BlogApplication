package com.blogapp.all.service;

import com.blogapp.all.dto.request.PostRequest;
import com.blogapp.all.dto.response.PostResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    ResponseEntity<PostResponse> savePost(PostRequest postRequest);
}
