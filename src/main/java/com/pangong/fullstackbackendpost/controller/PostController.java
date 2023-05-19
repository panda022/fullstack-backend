package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//test on the local host
@CrossOrigin("http://localhost:3000")

@RequestMapping("/api/posts")

//change the origin as deploy to aws
//@CrossOrigin(origins = "*")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //create post
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //get all posts within rest api
    @GetMapping
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePostById(@Valid @RequestBody PostDto postDto,@PathVariable(name = "id") long id){
        return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable(name="id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post with id "+id+"has been deleted successfully",HttpStatus.OK);
    }





}
