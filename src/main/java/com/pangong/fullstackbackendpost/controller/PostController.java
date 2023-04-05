package com.pangong.fullstackbackendpost.controller;

import com.pangong.fullstackbackendpost.exception.PostNotFoundException;
import com.pangong.fullstackbackendpost.model.Post;
import com.pangong.fullstackbackendpost.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//test on the local host
@CrossOrigin("http://localhost:3000")

//change the origin as deploy to aws
//@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping("/api/post")
    Post newPost(@RequestBody Post newPost) {
        return postRepository.save(newPost);
    }

    @GetMapping("/api/posts")
    List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @GetMapping("/api/post/{id}")
    Post getPostById(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException(id));
    }

    @PutMapping("/api/post/{id}")
    Post updatePost(@RequestBody Post newPost, @PathVariable Long id) {
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(newPost.getTitle());
                    post.setImage(newPost.getImage());
                    post.setAddress(newPost.getAddress());
                    post.setDescription(newPost.getDescription());

                    return postRepository.save(post);
                }).orElseThrow(() -> new PostNotFoundException(id));
    }

    @DeleteMapping("/api/post/{id}")
    String deletePost(@PathVariable Long id){
        if(!postRepository.existsById(id)){
            throw new PostNotFoundException(id);
        }
        postRepository.deleteById(id);
        return  "Post with id "+id+" has been deleted success.";
    }

}
