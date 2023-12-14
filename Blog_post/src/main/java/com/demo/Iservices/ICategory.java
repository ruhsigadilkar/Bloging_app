package com.demo.Iservices;

import java.util.List;

import com.demo.dtos.CategoryDto;
import com.demo.entities.Category;

public interface ICategory {

	CategoryDto createcategory(CategoryDto cat);

	CategoryDto getcategory(Long id);

	List<CategoryDto> getallCategory();

	CategoryDto updateCategory(Long id, CategoryDto dto);

	void deleteCategory(Long id);

}
