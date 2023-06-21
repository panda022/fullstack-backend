package com.pangong.fullstackbackendpost.service.impl;

import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.dtos.PostResponse;
import com.pangong.fullstackbackendpost.exception.ResourceNotFoundException;
import com.pangong.fullstackbackendpost.model.Post;
import com.pangong.fullstackbackendpost.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock private PostRepository postRepository;
    @Mock private ModelMapper modelMapper;
    private PostServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new PostServiceImpl(postRepository,modelMapper);
    }

    @AfterEach
        //ensure for each test, we have a clean state
    void tearDown() {
        postRepository.deleteAll();
    }

    @Test
    void createPost() {
        //given
        PostDto postDto = new PostDto("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        when(underTest.mapToEntity(postDto)).thenReturn(post);
        //when
        underTest.createPost(postDto);
        //then
        verify(postRepository).save(post);
    }

    @Test
    void getAllPosts() {
        // Prepare test data
        int pageNo = 0;
        int pageSize = 1;
        String sortBy = "title";
        String sortDir = "ASC";
        List<Post> mockedPosts = List.of(new Post("www.image.jpg", "postTitle1", "Test this post", "test search post"));

        // Stub the behavior of postRepository.findAll
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
        Page<Post> mockedPage = new PageImpl<>(mockedPosts, pageable, mockedPosts.size());
        when(postRepository.findAll(pageable)).thenReturn(mockedPage);

        // Call the method under test
        PostResponse result = underTest.getAllPosts(pageNo, pageSize, sortBy, sortDir);

        // Verify that the postRepository.findAll method was called with the correct pageable argument
        verify(postRepository).findAll(pageable);


    }

    @Test
    void getPostById() {
        //given
        long id = 0;
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        // when
        underTest.getPostById(id);
        // then (verify the postRepository.findById(id) method was invoked)
//        verify(postRepository).findById(id);
        //then(verify the Argument Capture)
        ArgumentCaptor<Long> postIdArgumentCaptor =
                ArgumentCaptor.forClass(long.class);

        verify(postRepository)
                .findById(postIdArgumentCaptor.capture());

        long capturedId = postIdArgumentCaptor.getValue();

        assertThat(capturedId).isEqualTo(id);
    }

    @Test
    void updatePostByIdSuccessfully() {
        //given
        PostDto postDto = new PostDto("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        long id = 0;
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        //when
        underTest.updatePostById(postDto,id);
        //then
        verify(postRepository).save(post);
    }

    @Test
    void updatePostByIdUnsuccessfully() {
        //given
        long id = 0;
        PostDto postDto = new PostDto("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        // when
        assertThatThrownBy(() -> underTest.updatePostById(postDto,id))
                .isInstanceOf( ResourceNotFoundException.class)
                .hasMessageContaining("post","id",id);
        // then (verify the postRepository.save(post) method was not invoked)
        verify(postRepository,never()).save(post);
    }

    @Test
    void deletePostByIdSuccessfully() {
        //given
        long id = 0;
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        // when
        underTest.deletePostById(id);
        // then (verify the postRepository.findById(id) method was invoked)
        verify(postRepository).delete(post);
    }


    @Test
    void deletePostByIdUnsuccessfully() {
        //given
        long id = 0;
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        // If the post is found, return Optional.of(post)
        // If the post is not found, return Optional.empty() not null.
        when(postRepository.findById(id)).thenReturn(Optional.empty());
        // when
        //underTest.deletePostById(id);
        assertThatThrownBy(() -> underTest.deletePostById(id))
                .isInstanceOf( ResourceNotFoundException.class)
                .hasMessageContaining("post","id",id);
        // then (verify the postRepository.findById(id) method was  not invoked)
        verify(postRepository,never()).delete(post);
    }

    @Test
    void searchPosts() {
        //given
        String query = "testPost";
        //when
        underTest.searchPosts(query);
        //then
        verify(postRepository).searchPosts(query);
    }

    @Test
    void mapToDto() {
        //given
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        //when
        underTest.mapToDto(post);
        //then
        verify(modelMapper).map(post,PostDto.class);
    }

    @Test
    void mapToEntity() {
        //given
        PostDto postDto = new PostDto("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        //when
        underTest.mapToEntity(postDto);
        //then
        verify(modelMapper).map(postDto,Post.class);
    }
}