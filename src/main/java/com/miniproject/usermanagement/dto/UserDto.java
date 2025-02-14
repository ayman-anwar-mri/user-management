package com.miniproject.usermanagement.dto;

import org.springframework.web.multipart.MultipartFile;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import com.miniproject.usermanagement.service.impl.*;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String userName;
    private String gender;
    private String emailId;
    private MultipartFile file;

    public UserDto(String userName,String gender,String emailId,MultipartFile file){
        this.userName = userName;
        this.gender = gender;
        this.emailId = emailId;
        this.name = uploadImage(file);
        
        }
        
        private String uploadImage(MultipartFile file2) {
            String fileName = file2.getOriginalFilename();
            int dotIndex = fileName.lastIndexOf('.');
            fileName = fileName.substring(0, dotIndex);  
            return fileName;
        }
        
        public UserDto(Long id,String name,String userName,String gender,String emailId){
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.emailId = emailId;
    }
}
