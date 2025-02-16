package com.miniproject.usermanagement.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
        
        // check if username or email id already exists
        checkMultipleUsers(user);
        
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void checkMultipleUsers(User user) {
        
        // to check if username already exists
        List<User> userListsUsername = userRepository.findByUserName(user.getUserName());
        if(userListsUsername.size()>=1){ 
            throw new MultipleUsernameException("Username already exists!");
        }

        // to check if email id already exists
        List<User> userListsEmailId = userRepository.findByEmailId(user.getEmailId());
        System.out.println(userListsEmailId.toString());
        if(userListsEmailId.size()>=1){
            throw new MultipleUsernameException("Email id already exists!");
        }
    }

    @Override
    public UserDto getAccountById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new MultipleUsernameException("User doesn't exist!"));
        return UserMapper.mapToUserDto(user);
    }
    
    @Override
    public List<UserDto> getAllAccounts(){
        List<User> users = userRepository.findAll();
        return users.stream().map((user)-> UserMapper.mapToUserDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, Map<String , String> updateDetails ){
        User user = userRepository.findById(id).orElseThrow(()-> new MultipleUsernameException("User doesn't exist!"));
        Set updateFields = updateDetails.keySet(); // column fields

        Iterator value = updateFields.iterator();

        while(value.hasNext()){
            String field =(String) value.next(); // coulumn name
            String fieldValue = updateDetails.get(field); // value to be updated 
            switch (field) {
                case "name":
                    user.setName(fieldValue);
                    break;

                case "userName":
                    checkMultipleUsername(fieldValue);
                    user.setUserName(fieldValue);
                    break;
                
                case "gender":
                    user.setGender(fieldValue);
                    break;
                
                case "emailId":
                    checkMultipleEmail(fieldValue);
                    user.setEmailId(fieldValue);
                    break;

                
                default:
                    throw new MultipleUsernameException("Incorrect field value provided!");
            }
        }
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }

    @Override
    public void checkMultipleUsername(String userName){
        List<User> userListsUsername = userRepository.findByUserName(userName);
        if(userListsUsername.size()>=1){ 
            throw new MultipleUsernameException("Username already exists!");
        }
    }

    @Override
    public void checkMultipleEmail(String emailId){
        List<User> userListsUsername = userRepository.findByEmailId(emailId);
        if(userListsUsername.size()>=1){ 
            throw new MultipleUsernameException("Email id already exists!");
        }
    }

    @Override
    public void deleteAccount(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new MultipleUsernameException("User doesn't exist!"));
        userRepository.deleteById(id);
    }

    @Override
    public String uploadImage(MultipartFile file) throws IOException{
        String fileName = file.getOriginalFilename();
        String path = "images/";
        String filePath = path + File.separator + fileName;
        int dotIndex = fileName.lastIndexOf('.');
        fileName = fileName.substring(0, dotIndex);  

        File f = new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        Files.copy(file.getInputStream(),Paths.get(filePath));
        return fileName;
    }
}
