package com.pangong.fullstackbackendpost.repository;

import com.pangong.fullstackbackendpost.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailOrUsername(String email,String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
