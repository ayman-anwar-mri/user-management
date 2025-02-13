package com.miniproject.usermanagement.mapper;

import com.miniproject.usermanagement.dto.UserDto;
import com.miniproject.usermanagement.entity.User;

public class UserMapper {

    public static User mapToUser(UserDto userDto){
        User user = new User(
            userDto.getId(),
            userDto.getName(),
            userDto.getUserName(),
            userDto.getGender(),
            userDto.getEmailId()
        );

        return user;
    }

    public static UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto(
            user.getId(),
            user.getName(),
            user.getUserName(),
            user.getGender(),
            user.getEmailId()
        );
        
        return userDto;
    }
}
