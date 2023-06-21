package com.pangong.fullstackbackendpost.repository;

import com.pangong.fullstackbackendpost.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    //JPQL
    @Query("SELECT p FROM Post p WHERE "+"p.title LIKE CONCAT('%',:query,'%')"
    +"Or p.description LIKE CONCAT('%',:query,'%')"+"Or p.content LIKE CONCAT('%',:query,'%')")
    List<Post> searchPosts(String query);

    @Query(value = "SELECT * FROM posts p WHERE "+"p.title LIKE CONCAT('%',:query,'%')"
            +"Or p.description LIKE CONCAT('%',:query,'%')"
            +"Or p.content LIKE CONCAT('%',:query,'%')",nativeQuery = true)
    //native sql search
    List<Post> searchPostsSQL(String query);




}
