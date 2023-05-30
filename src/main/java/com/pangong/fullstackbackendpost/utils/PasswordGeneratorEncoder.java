package com.pangong.fullstackbackendpost.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGeneratorEncoder {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("pan"));
        System.out.println(passwordEncoder.encode("admin"));
        //print out
        //$2a$10$19zgOFWhqi00UkuuRkCxdewcCAjjQ5LydMDSjfzG6cY1jYWURybF.
        //$2a$10$TYtcUNWskIL148b4vu3F5.IygrlHiflPfsVswfZ.T6VfZFH3arFV2
    }
}
