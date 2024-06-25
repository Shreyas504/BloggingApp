package com.shreyas.blog.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryDto {

	
	private Integer categoryId;
	@NotEmpty
	@Size(min = 4,message = "Category Title must be min of 4 characters")
	private String categoryTitle;
	
	@Size(min = 4,message = "Category Description must be min of 4 characters")
	private String categoryDescription;

	
}
