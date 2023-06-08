package com.pangong.fullstackbackendpost.service.impl;

import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.exception.ResourceNotFoundException;
import com.pangong.fullstackbackendpost.model.Post;
import com.pangong.fullstackbackendpost.repository.PostRepository;
import com.pangong.fullstackbackendpost.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        //convert Dto to entity
        Post post = mapToEntity(postDto);

        Post newPost = postRepository.save(post);
        //covert entity to dto
        PostDto postResponse = mapToDto(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePostById(PostDto postDto, long id) {
        //get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));

        //update the content of postDto,changes the state of the in-memory Java object
        post.setImage(postDto.getImage());
        post.setDescription(postDto.getDescription());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());

        //update the corresponding record of post entity in the database.
        postRepository.save(post);

        return  mapToDto(post);


    }

    @Override
    public void deletePostById(long id) {
        //get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> searchPosts(String query) {
        List<Post> posts = postRepository.searchPosts(query);
        return posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
    }

    //help method, which converts Entity into Dto
    private PostDto mapToDto(Post post){
        PostDto postDto = modelMapper.map(post,PostDto.class);
        return postDto;

//        PostDto postDto = new PostDto();
//        postDto.setId(post.getId());
//        postDto.setTitle(post.getTitle());
//        postDto.setDescription(post.getDescription());
//        postDto.setContent(post.getContent());
//        postDto.setImage(post.getImage());
//        return postDto;
    }
    //help method which converts Dto to entity
    private Post mapToEntity(PostDto postDto){
        Post post = modelMapper.map(postDto,Post.class);
        return post;

//        Post post = new Post();
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        post.setImage(postDto.getImage());
//        return post;
    }
}
