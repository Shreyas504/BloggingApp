package com.shreyas.blog.services;

import java.util.List;

import com.shreyas.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categroyId);

	void deleteCategory(Integer categroyId);
	
	CategoryDto getCategory(Integer categroyId);
	
	List<CategoryDto> getCategories();


}
