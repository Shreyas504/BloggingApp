package com.shreyas.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.query.Page;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.shreyas.blog.entities.Category;
import com.shreyas.blog.entities.Post;
import com.shreyas.blog.entities.User;
import com.shreyas.blog.exceptions.ResourceNotFoundException;
import com.shreyas.blog.payloads.PagerResponse;
import com.shreyas.blog.payloads.PostDto;
import com.shreyas.blog.payloads.UserDto;
import com.shreyas.blog.repositories.CategoryRepo;
import com.shreyas.blog.repositories.PostRepo;
import com.shreyas.blog.repositories.UserRepo;
import com.shreyas.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	private Object object;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		
		
		Post post = modelMapper.map(postDto, Post.class);
		
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = postRepo.save(post);
		return modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepo.save(post);
		
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepo.delete(post);
		
	}

	@Override
	public PagerResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

		Sort sortingProp = (sortDir.equalsIgnoreCase("dsc"))?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sortingProp);
		
		org.springframework.data.domain.Page<Post> pagePosts = postRepo.findAll(p);
		
		List<Post> allPosts =  pagePosts.getContent();
		
		List<PostDto> postsDto = allPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		
		PagerResponse pageResponse = new PagerResponse();
		pageResponse.setPosts(postsDto);
		pageResponse.setLast(pagePosts.isLast());
		pageResponse.setPageNumber(pagePosts.getNumber());
		pageResponse.setPageSize(pagePosts.getSize());
		pageResponse.setTotalPages(pagePosts.getTotalPages());
		pageResponse.setTotalEntries(pagePosts.getTotalElements());
		
		
		
		return pageResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		
		return modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {		
		Category cat = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
		List<Post> posts =postRepo.findByCategory(cat);
		List<PostDto> postDtos =  posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> userPosts = postRepo.findByUser(user);
		
		List<PostDto> postsDto = userPosts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		return postsDto;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		
		List<Post> posts = postRepo.findByTitleContaining(keyword);
		List<PostDto> dtoPosts = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

		return dtoPosts;
	}

}
