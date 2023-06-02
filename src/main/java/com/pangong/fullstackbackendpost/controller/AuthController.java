package com.pangong.fullstackbackendpost.controller;


import com.pangong.fullstackbackendpost.dtos.LoginDto;
import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.dtos.RegisterDto;
import com.pangong.fullstackbackendpost.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Repeatable;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Login REST API
    //value ={"",""} multiple url for rest api
    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        String response = authService.login(loginDto);
        return ResponseEntity.ok(response);
    }
    //Register REST API
    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> login(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
