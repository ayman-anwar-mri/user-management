package com.miniproject.usermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.usermanagement.dto.UserDto;
import com.miniproject.usermanagement.entity.User;
import com.miniproject.usermanagement.mapper.UserMapper;
import com.miniproject.usermanagement.repository.UserRepository;
import com.miniproject.usermanagement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public UserDto createAccount(UserDto userDto) {
        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }



}
