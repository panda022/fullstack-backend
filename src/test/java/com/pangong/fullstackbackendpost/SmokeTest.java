package com.pangong.fullstackbackendpost;

import static org.assertj.core.api.Assertions.assertThat;

import com.pangong.fullstackbackendpost.controller.PostController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private PostController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}