package com.myblog3.controller;

import com.myblog3.payload.CommentDto;
import com.myblog3.service.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController
{
    private CommentServices commentServices;

    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    //http://localhost:9091/api/comments?postId=1
    @PostMapping
    public ResponseEntity<CommentDto> createComments(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto dtos=commentServices.createComment(commentDto,postId);
        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }
    //http://localhost:9091/api/comments/3
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id)
    {
        commentServices.deleteComment(id);
        return new ResponseEntity<>("Comment is deleted!!...",HttpStatus.OK);
    }
    //http://localhost:9091/api/comments/3/post/1
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId)
    {
        CommentDto dto=commentServices.updateComment(id,commentDto,postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
