package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.dtos.PostResponse;
import com.pangong.fullstackbackendpost.service.PostService;
import com.pangong.fullstackbackendpost.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//test on the local host
@CrossOrigin("http://localhost:3000")

@RequestMapping("/api/posts")
@Tag(
        name = "CRUD REST APIS for POST"
)

//change the origin as deploy to aws
//@CrossOrigin(origins = "*")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create post
    @PostMapping
    @Operation(
            summary = "Create Post REST API",
            description = "Create Post REST API is used to save post into database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

//    //get all posts within rest api
//    @GetMapping
//    public List<PostDto> getAllPosts(){
//        return postService.getAllPosts();
//    }

    //get all posts within rest api with pagination
    @GetMapping
    @Operation(
            summary = "Get Post REST API",
            description = "This API is used to get posts from the database within the pageNo and pageSize"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public PostResponse getAllPosts(@RequestParam(value = "pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
                                    @RequestParam(value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
                                    @RequestParam(value = "sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
                                    @RequestParam(value = "sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get Post By id REST API",
            description = "This API is used to get the specific post from the database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }



    @GetMapping("/search")
    @Operation(
            summary = "Serach Post REST API",
            description = "This API is used to get specific post from the database by the query requirement"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam("query") String query){
        return ResponseEntity.ok(postService.searchPosts(query));
    }



    @PutMapping("/{id}")
    @Operation(
            summary = "Update Post REST API",
            description = "This API is used to update specific post from the database by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Post REST API",
            description = "This API is used to delete specific post from the database by the id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    @SecurityRequirement(
            name = "Bear Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post with id "+id+"has been deleted successfully",HttpStatus.OK);
    }







}
