package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.model.Post;
import com.pangong.fullstackbackendpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/post")
    Post newUser(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }

    @GetMapping("/posts")
    List<Post> getAllPosts() {
        return postRepository.findAll();
    }


}
