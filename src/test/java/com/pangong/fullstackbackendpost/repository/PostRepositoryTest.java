package com.pangong.fullstackbackendpost.repository;

import com.pangong.fullstackbackendpost.model.Post;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


import java.util.List;


/**
 * @ExtendWith(SpringExtension.class) is a general-purpose annotation used to enable Spring integration in JUnit 5 tests,
 * while @DataJpaTest is a Spring Boot-specific annotation that provides
 * additional testing capabilities specifically for JPA repositories.
 */
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DataJpaTest
class PostRepositoryTest {
    @Autowired
    //When using JUnit, Spring doesn't automatically perform dependency injection
    // for test classes unless explicitly enable it. So use @ExtendWith(SpringExtension.class) and @SpringBootTest
    private PostRepository underTest;

    @AfterEach
    //ensure for each test, we have a clean state
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void itShouldSearchPostsSuccessful() {
        //given
        Post post = new Post("www.image.jpg",
                "postTitle9",
                "Test this post",
                "test search post");
        underTest.save(post);
        // when
        List<Post> posts = underTest.searchPosts("postTitle9");
        boolean isPostsContainsPost = posts.get(0).getTitle().contains("postTitle9");
        //boolean isPostsContainsPost = posts.contains(post);
        //then
        assertThat(isPostsContainsPost).isTrue();
    }

    @Test
    void itShouldSearchPostsUnsuccessfully() {
        //given
        Post post = new Post("www.image.jpg",
                "postTitle1",
                "Test this post",
                "test search post");
        underTest.save(post);
        // when
        List<Post> posts = underTest.searchPosts("postTitle9");
        boolean isPostsContainsPost = posts.contains(post);
        //boolean isPostsContainsPost = posts.contains(post);
        //then
        assertThat(isPostsContainsPost).isFalse();
    }
}