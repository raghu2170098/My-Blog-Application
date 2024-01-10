package com.myblog3.controller;

import com.myblog3.payload.PostDto;
import com.myblog3.repository.PostRepository;
import com.myblog3.service.PostServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostServices postServices;

    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        PostDto dto=postServices.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
