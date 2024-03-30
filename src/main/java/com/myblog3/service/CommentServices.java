package com.myblog3.service;

import com.myblog3.payload.CommentDto;

public interface CommentServices {

    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteComment(Long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
