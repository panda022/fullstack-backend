package com.pangong.fullstackbackendpost.service.impl;

import com.pangong.fullstackbackendpost.dtos.CommentDto;
import com.pangong.fullstackbackendpost.exception.BlogApiException;
import com.pangong.fullstackbackendpost.exception.ResourceNotFoundException;
import com.pangong.fullstackbackendpost.model.Comment;
import com.pangong.fullstackbackendpost.model.Post;
import com.pangong.fullstackbackendpost.repository.CommentRepository;
import com.pangong.fullstackbackendpost.repository.PostRepository;
import com.pangong.fullstackbackendpost.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        //retrieve post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post","id",postId)
        );
        //set post to comment entity
        comment.setPost(post);
        //save comment entity to DB
        Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        //retrieve comments by id
        List<Comment> comments = commentRepository.findByPostId(postId);
        //convent list of comment entities to list of comment dto entities
        List<CommentDto> commentDtos = comments.stream().map(comment->mapToDto(comment)).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {
        //retrieve post entity by post id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );
        //retrieve comment entity by comment id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId)
        );
        //check if the comment retrived by commentId match post retrived by postId
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to the post");
        }

        return mapToDto(comment);
    }

    @Override
    public CommentDto updateCommentById(long postId, long commentId, CommentDto commentRequest) {
        //retrieve post entity by post id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );
        //retrieve comment entity by comment id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId)
        );
        //check if the comment retrived by commentId match post retrived by postId
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to the post");
        }
        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());

        Comment updateComment = commentRepository.save(comment);
        return mapToDto(updateComment);

    }

    @Override
    public void deleteComment(long postId, long commentId) {
        //retrieve post entity by post id
        Post post = postRepository.findById(postId).orElseThrow(
                ()->new ResourceNotFoundException("Post","id",postId)
        );
        //retrieve comment entity by comment id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("Comment","id",commentId)
        );
        //check if the comment retrived by commentId match post retrived by postId
        if (!comment.getPost().getId().equals(post.getId())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"comment does not belong to the post");
        }
        commentRepository.delete(comment);
    }

    //helper method transfer Comment entity to Comment Dto
    private CommentDto mapToDto(Comment comment) {
        CommentDto commentDto = modelMapper.map(comment,CommentDto.class);
        return  commentDto;
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setBody(comment.getBody());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setName(comment.getName());
//        return  commentDto;
    }

    //helper method to transfer DTO to entity
    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = modelMapper.map(commentDto,Comment.class);
        return comment;

//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
//        return comment;
    }


}
