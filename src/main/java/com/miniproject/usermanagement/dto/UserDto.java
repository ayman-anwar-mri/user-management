package com.miniproject.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String gender;
    private String emailId;
}
