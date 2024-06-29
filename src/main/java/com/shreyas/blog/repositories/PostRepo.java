package com.shreyas.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shreyas.blog.entities.Category;
import com.shreyas.blog.entities.Post;
import com.shreyas.blog.entities.User;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer>{

	
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category cat);
	
	List<Post> findByTitleContaining(String title);
	
}
