package com.myblog3.service.impl;

import com.myblog3.entity.Comment;
import com.myblog3.entity.Post;
import com.myblog3.exception.ResourceNotFoundEception;
import com.myblog3.payload.CommentDto;
import com.myblog3.repository.CommentsRepository;
import com.myblog3.repository.PostRepository;
import com.myblog3.service.CommentServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentsServicesImpl implements CommentServices {
    private CommentsRepository commentsRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;

    public CommentsServicesImpl(CommentsRepository commentsRepository, PostRepository postRepository,ModelMapper modelMapper) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post=postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundEception("Post not found with id:"+postId));
//        Comment comment=mapToEntity(commentDto,post);
        Comment comment=mapToCommentEntity(commentDto);
        comment.setPost(post);
        Comment saveComment=commentsRepository.save(comment);
        CommentDto dto=mapToCommentDto(comment);

        return dto;
    }

    @Override
    public void deleteComment(Long id)
    {
       commentsRepository.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post=postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundEception("Post Not Found With Id:"+postId));
        Comment comment=commentsRepository.findById(id).orElseThrow(()-> new ResourceNotFoundEception("Comment not found with id:"+id));
           Comment comment1=mapToCommentEntity(commentDto);
           comment1.setId(comment.getId());
           comment1.setPost(post);
        Comment savecom=commentsRepository.save(comment1);
        return mapToCommentDto(savecom);

    }
    CommentDto mapToCommentDto(Comment comment)
    {
        CommentDto dto=modelMapper.map(comment,CommentDto.class);
        return dto;
    }
    Comment mapToCommentEntity(CommentDto commentDto)
    {
        Comment comment=modelMapper.map(commentDto,Comment.class);
        return comment;
    }

//    private CommentDto mapToDto(Comment saveComment) {
//        CommentDto dto=new CommentDto();
//        dto.setId(saveComment.getId());
//        dto.setText(saveComment.getText());
//        dto.setEmail(saveComment.getEmail());
//        return dto;
//    }
//
//    private Comment mapToEntity(CommentDto commentDto,Post post) {
//        Comment comment=new Comment();
//        comment.setEmail(commentDto.getEmail());
//        comment.setText(commentDto.getText());
//        comment.setPost(post);
//        return comment;
//    }
}
