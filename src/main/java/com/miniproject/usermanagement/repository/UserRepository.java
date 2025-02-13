package com.miniproject.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.miniproject.usermanagement.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
