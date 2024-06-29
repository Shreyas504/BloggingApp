package com.shreyas.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagerResponse {
	
	
	private long pageSize;
	private int pageNumber;
	private int totalPages;
	private long totalEntries;
	private boolean isLast;
	private List<PostDto> posts;

}
