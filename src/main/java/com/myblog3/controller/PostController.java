package com.myblog3.controller;

import com.myblog3.payload.PostDto;
import com.myblog3.repository.PostRepository;
import com.myblog3.service.PostServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private PostServices postServices;

    public PostController(PostServices postServices)
    {
        this.postServices = postServices;
    }
    //http://localhost:9091/api/posts
   // @PreAuthorize("hasRole('Admin')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto)
    {
        PostDto dto=postServices.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:9091/api/posts/particular?id=1
    @GetMapping("/particular")
    public ResponseEntity<PostDto> getPostById(@RequestParam long id)
    {
        PostDto dto =postServices.getPostById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
//    @GetMapping
//    public List<PostDto> getAllPosts()
//    {
//        List<PostDto> postDtos=postServices.getAllPosts();
//        return  postDtos;
//    }
//http://localhost:9091/api/posts?pageNo=0&pageSize=5
//    @GetMapping
//    public List<PostDto> getAllPosts(@RequestParam(name="pageNo",defaultValue = "0", required = false) int pageNo,
//                                     @RequestParam(name="pageSize",defaultValue = "3", required = false) int pageSize
//
//                                     )
//    {
//        List<PostDto> postDtos=postServices.getAllPosts(pageNo,pageSize);
//        return  postDtos;
//    }
    //http://localhost:9091/api/posts?pageNo=0&pageSize=5&sortBy=title
//    @GetMapping
//    public List<PostDto> getAllPosts(@RequestParam(name="pageNo",defaultValue = "0", required = false) int pageNo,
//                                 @RequestParam(name="pageSize",defaultValue = "3", required = false) int pageSize,
//                                 @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
//                                    @RequestParam(name="sortDir",defaultValue = "id",required = false) String sortDir
//                                    )
//    {
//        List<PostDto> postDtos=postServices.getAllPosts(pageNo,pageSize,sortBy,);
//        return  postDtos;
//    }
//http://localhost:9091/api/posts?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
    @GetMapping
    public List<PostDto> getAllPosts(@RequestParam(name="pageNo",defaultValue = "0", required = false) int pageNo,
                                     @RequestParam(name="pageSize",defaultValue = "3", required = false) int pageSize,
                                     @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy,
                                     @RequestParam(name="sortDir",defaultValue = "id",required = false) String sortDir
    )
    {
//        List<PostDto> postDtos=postServices.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        List<PostDto> postDtos=postServices.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return  postDtos;
    }

}
