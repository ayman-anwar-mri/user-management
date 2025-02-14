package com.miniproject.usermanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.miniproject.usermanagement.dto.UserDto;
import com.miniproject.usermanagement.entity.User;

public interface UserService {

    UserDto createAccount(UserDto userDto);
    void checkMultipleUsers(User user); 
    UserDto getAccountById(Long id);
    List<UserDto> getAllAccounts();
    UserDto updateUser(Long id,Map<String,String> updateDetails);
    void checkMultipleUsername(String userName);
    void checkMultipleEmail(String emailId);
    void deleteAccount(Long id);
    String uploadImage(MultipartFile file);

}
