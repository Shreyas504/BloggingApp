package com.shreyas.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shreyas.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
