package com.miniproject.usermanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.usermanagement.dto.UserDto;
import com.miniproject.usermanagement.entity.User;
import com.miniproject.usermanagement.exception.MultipleUsernameException;
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
        List<User> userLists = userRepository.findByUserName(user.getUserName());
        System.out.println(userLists.toString());
        
        if(userLists.size()>=1){
            throw new MultipleUsernameException("Username already exists!");
        } 
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }



}
