package com.myblog3.service.impl;

import com.myblog3.entity.Post;
import com.myblog3.payload.PostDto;
import com.myblog3.repository.PostRepository;
import com.myblog3.service.PostServices;
import org.springframework.stereotype.Service;

@Service
public class PostServicesImpl implements PostServices {
    private PostRepository postRepository;

    public PostServicesImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post= new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost=postRepository.save(post);
        PostDto postD=new PostDto();
        postD.setTitle(savePost.getTitle());
        postD.setDescription(savePost.getDescription());
        postD.setContent(savePost.getContent());
        return postD;
    }
}
