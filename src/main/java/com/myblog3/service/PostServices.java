package com.myblog3.service;

import com.myblog3.payload.PostDto;

import java.util.List;

public interface PostServices {
    PostDto getPostById(long id);


    PostDto createPost(PostDto postDto);
    //List<PostDto> getAllPosts();
    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir);
}
