package com.pangong.fullstackbackendpost.service.impl;

import com.pangong.fullstackbackendpost.dtos.LoginDto;
import com.pangong.fullstackbackendpost.dtos.RegisterDto;
import com.pangong.fullstackbackendpost.exception.BlogApiException;
import com.pangong.fullstackbackendpost.model.Role;
import com.pangong.fullstackbackendpost.model.User;
import com.pangong.fullstackbackendpost.repository.RoleRepository;
import com.pangong.fullstackbackendpost.repository.UserRepository;
import com.pangong.fullstackbackendpost.security.JwtTokenProvider;
import com.pangong.fullstackbackendpost.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;
    private JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                           ModelMapper modelMapper,JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOrEmail(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);
        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {
        //check whether username exists in db
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"username has exits");
        }
        //check whether email exists in db
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"email has exits");
        }
        //creat new user obj and save it in the userRepository
        //User user = new User();
        //user.setName(registerDto.getName());
        User user = mapToEntity(registerDto);
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<Role>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully";
    }

    private User mapToEntity(RegisterDto registerDto){
        User user = modelMapper.map(registerDto,User.class);
        return user;
    }
}
