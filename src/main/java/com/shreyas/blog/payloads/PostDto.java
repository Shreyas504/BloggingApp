package com.shreyas.blog.payloads;

import java.util.Date;

import com.shreyas.blog.entities.Category;
import com.shreyas.blog.entities.User;

import lombok.Data;

@Data
public class PostDto {

	private Integer postId;
	
	private String title;
	
	private String content;
		
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
