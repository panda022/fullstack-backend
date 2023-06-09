package com.pangong.fullstackbackendpost.service;

import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.dtos.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

//    List<PostDto> getAllPosts();
    PostResponse getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto, long id);

    void deletePostById(long id);

    List<PostDto> searchPosts(String query);
}

