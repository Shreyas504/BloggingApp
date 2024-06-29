package com.shreyas.blog.services;

import java.util.List;

import com.shreyas.blog.entities.Post;
import com.shreyas.blog.payloads.PagerResponse;
import com.shreyas.blog.payloads.PostDto;

public interface PostService {
	
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PagerResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	List<PostDto> searchPosts(String keyword);
}
