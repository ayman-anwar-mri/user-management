package com.miniproject.usermanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<UserDto> addUser( @RequestParam("userName") String userName,
                                            @RequestParam("gender") String gender,
                                            @RequestParam("emailId") String emailId,
                                            @RequestParam("file") MultipartFile file){
        UserDto userDto = new UserDto(userName, gender, emailId,file);
        System.out.println(userDto.getName());
        return new ResponseEntity<>(userService.createAccount(userDto),HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getAccountById(@PathVariable Long id){
        UserDto userDto = userService.getAccountById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllAccounts(){
        List<UserDto> users = userService.getAllAccounts();
        return ResponseEntity.ok(users);
    }

    // update name REST API
    @PutMapping("/{id}/update")
    public ResponseEntity<UserDto> updateUserDetails( @PathVariable Long id,
                                               @RequestBody  Map<String,String> requestBody){
        
        UserDto userDto = userService.updateUser(id, requestBody);
        return ResponseEntity.ok(userDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        userService.deleteAccount(id);
        return ResponseEntity.ok("User is deleted successfully!");
    }

}

