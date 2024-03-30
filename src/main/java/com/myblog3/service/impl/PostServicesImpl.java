package com.myblog3.service.impl;

import com.myblog3.entity.Post;
import com.myblog3.exception.ResourceNotFoundEception;
import com.myblog3.payload.PostDto;
import com.myblog3.repository.PostRepository;
import com.myblog3.service.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServicesImpl implements PostServices {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServicesImpl(PostRepository postRepository,ModelMapper modelMapper)
    {
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }
    @Override
    public PostDto createPost(PostDto postDto) {
        Post post= mapToEntity(postDto);
        Post savePost=postRepository.save(post);
        return mapToDto(savePost);
    }

    @Override
    public PostDto getPostById(long id) {
        Post post= postRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundEception("Post not Found with id:"+id)
        );

        return mapToDto(post);
    }
//    @Override
//    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy) {
//
//        Pageable pageable=PageRequest.of(pageNo,pageSize, Sort.by(sortBy));
//       // Pageable pageable =PageRequest.of(pageNo,pageSize);
//        Page<Post> pagePost =postRepository.findAll(pageable);
//        List<Post> post=pagePost.getContent();
//        List<PostDto> dtos=post.stream().map(p -> mapToPostDto(p)).collect(Collectors.toList());
//        return dtos;
//    }
@Override
public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy,String sortDir) {
    Sort sort=(sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
   // Pageable pageable=PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
     Pageable pageable=PageRequest.of(pageNo,pageSize, sort);
    // Pageable pageable =PageRequest.of(pageNo,pageSize);
    Page<Post> pagePost =postRepository.findAll(pageable);
    List<Post> post=pagePost.getContent();
    List<PostDto> dtos=post.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
    return dtos;
}
//    public List<PostDto> getAllPosts() {
//        List<Post> posts=postRepository.findAll();
//        List<PostDto> dtos=posts.stream().map(post -> mapToPostDto(post)).collect(Collectors.toList());
//        return dtos;
//    }
    //-----------------------------------------------------------------------------
    //instead of this method we use ModelMapper class with add dependency maven modelmpper
    Post mapToEntity(PostDto postDto)
    {
        Post post = modelMapper.map(postDto, Post.class);
        return post;
    }
    PostDto mapToDto(Post post)
    {
        PostDto dto = modelMapper.map(post, PostDto.class);
        return dto;
    }
//    PostDto mapToPostDto(Post post)
//    {
//        PostDto dto=new PostDto();
//       dto.setId(post.getId());
//        dto.setContent(post.getContent());
//        dto.setDescription(post.getDescription());
//        dto.setTitle(post.getTitle());
//        return dto;
//    }
//    Post mapToPost(PostDto postDto)
//    {
//        Post post=new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        return post;
//    }
    //------------------------------------------------------------------------------------------------------

}
