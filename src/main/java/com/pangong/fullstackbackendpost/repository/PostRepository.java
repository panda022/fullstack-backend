package com.pangong.fullstackbackendpost.repository;

import com.pangong.fullstackbackendpost.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {


}
