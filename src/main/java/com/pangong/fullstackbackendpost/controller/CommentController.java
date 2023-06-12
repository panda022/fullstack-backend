package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.dtos.CommentDto;
import com.pangong.fullstackbackendpost.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(
        name = "CRUD REST APIS for Comment"
)
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //create comment
    @PostMapping("/posts/{postId}/comments")
    @Operation(
            summary = "Create Comment REST API",
            description = "This API is used to save comment into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @Valid @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId,commentDto), HttpStatus.CREATED);
    }


    //get comments by postId
    @GetMapping("/posts/{postId}/comments")
    @Operation(
            summary = "Get Comment By id REST API",
            description = "This API is used to get all the comments from the database by postId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public List<CommentDto> getCommentsByPostId(@PathVariable(name = "postId")long postId){
        return commentService.getCommentsByPostId(postId);
    }

    //get comment by postId and commentId
    @GetMapping("/posts/{postId}/comments/{id}")
    @Operation(
            summary = "Get Comment By id REST API",
            description = "This API is used to get all the comments from the database by postId and commentId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId){
        CommentDto commentDto = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    //update comment by postId and commentId
    @PutMapping("/posts/{postId}/comments/{id}")
    @Operation(
            summary = "Update Comment REST API",
            description = "This API is used to update specific commnet from the database by postId and commentId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable(value = "postId") long postId,
                                                     @PathVariable(value = "id") long commentId,
                                                        @Valid @RequestBody  CommentDto commentDto){
        CommentDto updatedCommentDto = commentService.updateCommentById(postId, commentId, commentDto);
        return new ResponseEntity<>(updatedCommentDto,HttpStatus.OK);


    }

    //delete comment by postId and commentId
    @DeleteMapping("/posts/{postId}/comments/{id}")
    @Operation(
            summary = "Delete Comment REST API",
            description = "This API is used to delete specific comment from the database by the postId and commentId"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> deleteCommentById(@PathVariable(value = "postId") long postId,
                                  @PathVariable(value = "id") long commentId){
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment delete successfully",HttpStatus.OK);
    }
}
