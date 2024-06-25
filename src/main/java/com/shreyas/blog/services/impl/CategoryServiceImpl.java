package com.shreyas.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shreyas.blog.entities.Category;
import com.shreyas.blog.exceptions.ResourceNotFoundException;
import com.shreyas.blog.payloads.CategoryDto;
import com.shreyas.blog.repositories.CategoryRepo;
import com.shreyas.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {

		Category c = modelMapper.map(categoryDto, Category.class);
		
		Category catSaved = categoryRepo.save(c);
		
		
		return modelMapper.map(catSaved, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categroyId) {
		
		Category category = categoryRepo.findById(categroyId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", categroyId));
		
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		category.setCategoryTitle(categoryDto.getCategoryTitle());		
		
		Category updatedCat = categoryRepo.save(category);
		 
		return modelMapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categroyId) {
		
		Category category = categoryRepo.findById(categroyId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", categroyId));
		
		categoryRepo.delete(category);
		
	}

	@Override
	public CategoryDto getCategory(Integer categroyId) {
		
		Category getCategory = categoryRepo.findById(categroyId).orElseThrow(() -> new ResourceNotFoundException("Category","Id", categroyId));
		
		
		return modelMapper.map(getCategory, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getCategories() {

		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> cats = categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

		
		return cats;
	}

}
