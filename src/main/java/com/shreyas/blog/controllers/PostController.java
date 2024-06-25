package com.shreyas.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shreyas.blog.payloads.PostDto;
import com.shreyas.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {

	
	@Autowired
	private PostService postService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto post, @PathVariable Integer userId, @PathVariable Integer categoryId){
		
		PostDto createPost = postService.createPost(post, userId, categoryId);
		
		return new ResponseEntity<PostDto>(createPost,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByUserId(@PathVariable Integer userId){
		
		List<PostDto> userPosts = postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(userPosts, HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getAllPostsByCategoryId(@PathVariable Integer categoryId){
		
		List<PostDto> categoryPosts = postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(categoryPosts, HttpStatus.OK);
		
	}
	
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		
		List<PostDto> posts = postService.getAllPost();
		
		return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		
		PostDto post = postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<?> deletePost(@PathVariable Integer postId) {
		postService.deletePost(postId);
		return new ResponseEntity<>("Post deleted Successfully!", HttpStatus.OK);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
		PostDto updatedPost= postService.updatePost(postDto,postId);
		return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}
	
}
