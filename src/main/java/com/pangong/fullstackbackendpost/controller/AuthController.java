package com.pangong.fullstackbackendpost.controller;


import com.pangong.fullstackbackendpost.dtos.JwtAuthResponse;
import com.pangong.fullstackbackendpost.dtos.LoginDto;
import com.pangong.fullstackbackendpost.dtos.PostDto;
import com.pangong.fullstackbackendpost.dtos.RegisterDto;
import com.pangong.fullstackbackendpost.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "POST REST APIS for Authentication"
)
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Login REST API
    //value ={"",""} multiple url for rest api
    @PostMapping(value = {"/login","/signin"})
    @Operation(
            summary = "Login or Signin REST API",
            description = "This API is used to login the user and set jwt token"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 SUCCESS"
    )
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    //Register REST API
    @PostMapping(value = {"/register","/signup"})
    @Operation(
            summary = "Register or signup REST API",
            description = "This API is used to register the user and save the user in the database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Http Status 201 CREATED"
    )
    public ResponseEntity<String> login(@RequestBody RegisterDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
