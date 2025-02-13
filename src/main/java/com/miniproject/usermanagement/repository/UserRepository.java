package com.miniproject.usermanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findByUserName(String userName);
    List<User> findByEmailId(String emailId);
}
