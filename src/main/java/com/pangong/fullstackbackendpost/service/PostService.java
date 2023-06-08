package com.pangong.fullstackbackendpost.service;

import com.pangong.fullstackbackendpost.dtos.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts();

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

    List<PostDto> searchPosts(String query);
}

