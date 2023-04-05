package com.pangong.fullstackbackendpost.exception;

public class PostNotFoundException extends RuntimeException{
    public PostNotFoundException(Long id){
        super("Could not find the post with the id"+id);
    }
}
