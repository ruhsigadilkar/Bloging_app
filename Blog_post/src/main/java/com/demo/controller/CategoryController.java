package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Iservices.ICategory;
import com.demo.dtos.CategoryDto;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cat")
public class CategoryController {

	@Autowired

	ICategory service;

	// createcategory

	@PostMapping("/add")
	ResponseEntity<CategoryDto> createcategory(@Valid @RequestBody CategoryDto dto) {
		CategoryDto resp = service.createcategory(dto);

		return new ResponseEntity<CategoryDto>(resp, HttpStatus.OK);

	}

	// getcategory
	@GetMapping("/{id}")
	ResponseEntity<CategoryDto> getcategory(@PathVariable long id) {
		CategoryDto resp = service.getcategory(id);

		return new ResponseEntity<CategoryDto>(resp, HttpStatus.OK);

	}

	// getallcategory

	@GetMapping()

	ResponseEntity<List<CategoryDto>> getcategory() {

		List<CategoryDto> categorylist = service.getallCategory();
		return new ResponseEntity<List<CategoryDto>>(categorylist, HttpStatus.OK);

	}

	// updatecatoegory

	@PutMapping("/upadte/{id}")

	ResponseEntity<CategoryDto> updatecategory(@PathVariable Long id, @RequestBody CategoryDto dto) {

		CategoryDto persistcategory = service.getcategory(id);

		persistcategory.setCategory_id(id);
		persistcategory.setCategorydescription(dto.getCategorydescription());
		persistcategory.setCategoryTitle(dto.getCategoryTitle());
		CategoryDto savecategory = service.updateCategory(id, persistcategory);
		return new ResponseEntity<CategoryDto>(savecategory, HttpStatus.OK);

	}

	// deletecaregory

	@DeleteMapping("/delete/{id}")
	ResponseEntity<String> deletecatagory(@PathVariable Long id) {

		CategoryDto persistcategory = service.getcategory(id);
		System.out.println("indside deleete");
		service.deleteCategory(id);

		return new ResponseEntity<String>("delete cataegoty "+id+" Successfuly " , HttpStatus.ACCEPTED);

	}
}
