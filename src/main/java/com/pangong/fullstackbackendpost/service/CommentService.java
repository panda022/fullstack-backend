package com.pangong.fullstackbackendpost.service;

import com.pangong.fullstackbackendpost.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentsByPostId(long postId);
    CommentDto getCommentById(long postId,long commentId);
    CommentDto updateCommentById(long postId,long commentId,CommentDto commentRequest);
    void deleteComment(long postId,long commentId);
}
