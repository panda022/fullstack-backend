package com.pangong.fullstackbackendpost.service;

import com.pangong.fullstackbackendpost.dtos.LoginDto;
import com.pangong.fullstackbackendpost.dtos.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
