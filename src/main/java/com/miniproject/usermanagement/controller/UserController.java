package com.miniproject.usermanagement.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.usermanagement.dto.UserDto;
import com.miniproject.usermanagement.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Add user REST API
    @PostMapping
    public ResponseEntity<UserDto> addUser( @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.createAccount(userDto),HttpStatus.CREATED);
    }

}
