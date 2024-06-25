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

import com.shreyas.blog.payloads.ApiResponse;
import com.shreyas.blog.payloads.CategoryDto;
import com.shreyas.blog.payloads.UserDto;
import com.shreyas.blog.services.CategoryService;
import com.shreyas.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategoryDto = categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createCategoryDto,HttpStatus.CREATED);
	}

	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		
		CategoryDto updatedCategory = categoryService.updateCategory(categoryDto, categoryId);
		
		return ResponseEntity.ok(updatedCategory);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
		
		categoryService.deleteCategory(categoryId);
		return new ResponseEntity<>(new ApiResponse("Category deleted successfully", true),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		
		List<CategoryDto> allCategories = categoryService.getCategories();
		
		return ResponseEntity.ok(allCategories);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(categoryService.getCategory(categoryId));
	}
}

