package com.example.demo.service;


import com.example.demo.entity.Category;
import com.example.demo.payload.CategoryDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;



    public Result addCategoryService(CategoryDto categoryDto){

        Category category = new Category();

        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null){

            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());

            if (!optionalCategory.isPresent())
                return new Result("Such parent category not exist",false);

            category.setParentCategoty(optionalCategory.get());

        }

        categoryRepository.save(category);

        return new Result("Successfully added",true);


    }


    public Result editCategory(Integer id, CategoryDto categoryDto){


        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent()){

            return new Result("Such category not found",false);
        }

        Category category = optionalCategory.get();

        category.setName(categoryDto.getName());

        if (categoryDto.getParentCategoryId() != null){

            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());

            if (!optionalCategory1.isPresent())
                return new Result("Such parent category not exist",false);

            category.setParentCategoty(optionalCategory.get());

        }

        categoryRepository.save(category);

        return new Result("Successfully edited",true);




    }

    public List<Category> getCategories(){

        List<Category> categories = categoryRepository.findAll();

        return categories;

    }


    public Category getCategory(Integer id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent()){

            return new Category();

        }

        return optionalCategory.get();

    }


    public Result deleteCategory(Integer id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if (!optionalCategory.isPresent()){

            return new Result("Such category do not exist",false);

        }

        categoryRepository.deleteById(id);

        return new Result("Successfully deleted",true);

    }



}
