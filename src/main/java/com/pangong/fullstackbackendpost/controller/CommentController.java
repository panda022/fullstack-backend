package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.dtos.CommentDto;
import com.pangong.fullstackbackendpost.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //create comment
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }

    //get comments by postId
    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId")long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //get comment by postId and commentId
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    //update comment by postId and commentId
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId,
                                                        @Valid @RequestBody  CommentDto commentDto){
        CommentDto updatedCommentDto = commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto,HttpStatus.OK);


    }

    //delete comment by postId and commentId
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                  @PathVariable(value = "id") long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment delete successfully",HttpStatus.OK);
    }
}
