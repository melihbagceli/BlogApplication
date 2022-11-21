package com.blogapp.all.service.impl;

import com.blogapp.all.entity.User;
import com.blogapp.all.repository.PostRepository;
import com.blogapp.all.dto.request.PostRequest;
import com.blogapp.all.dto.response.PostResponse;
import com.blogapp.all.service.PostService;
import com.blogapp.all.entity.Post;
import com.blogapp.all.utils.PostUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    PostRepository postRepository;

    @Override
    public ResponseEntity<PostResponse> savePost(PostRequest postRequest) {
        //Fields
        String message;
        Post postDb = null;
        HttpStatus httpStatus;
        PostResponse postResponse;

        //Later Will Be Added JWT
        User user = new User();
        user.setId(1);
        postRequest.setAuthor(user);

        Post post = PostUtils.postRequestToPost(postRequest);
        try{
            Optional<Post> postInDb = postRepository.findBySlug(postRequest.getSlug());
            if(postInDb.isPresent()){
                httpStatus = HttpStatus.BAD_REQUEST;
                message = String.format("Post with slug %s already exists in database. You should change your slug of your post.",postInDb.get().getSlug());
            }
            else{
                Optional<Post> parentPost = postRepository.findById((long) postRequest.getParentPostId());
                if(parentPost.isPresent())
                    post.setParentPost(parentPost.get());
                postDb = postRepository.save(post);
                //System.out.println(postDb.getParentPost().getParentPost().getSlug());
                httpStatus = HttpStatus.CREATED;
                message = String.format("Post Added Succesfully with Id %s",postDb.getId());
            }
            postResponse = PostUtils.postToPostResponse(postDb, message);

        }catch (Exception e){
            message = String.format("There is an internal error while trying to add post to the database. Please try again.");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            postResponse = PostUtils.postToPostResponse(postDb, message);
            postResponse.setError(e.getMessage());
        }
        return new ResponseEntity<>(postResponse, httpStatus);
    }
}
