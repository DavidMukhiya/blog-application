package com.david.blogapplication.services.impl;

import com.david.blogapplication.entities.Category;
import com.david.blogapplication.exceptions.ResourceNotFoundException;
import com.david.blogapplication.payloads.CategoryDto;
import com.david.blogapplication.repositories.CategoryRepo;
import com.david.blogapplication.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category newCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updateCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updateCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category deleteCategory = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Cateogry ", " Category Id", categoryId));
        this.categoryRepo.delete(deleteCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category ", " Category Id ", categoryId));
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> allCategory = this.categoryRepo.findAll();
        List<CategoryDto> allCategoryDto = allCategory.stream().map((category -> modelMapper.map(category, CategoryDto.class))).collect(Collectors.toList());
        return allCategoryDto;
    }
}
