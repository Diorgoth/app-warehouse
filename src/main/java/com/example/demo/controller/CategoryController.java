package com.example.demo.controller;

import com.example.demo.entity.Category;

import com.example.demo.payload.CategoryDto;
import com.example.demo.payload.Result;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    @Autowired
    CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto){

        Result result = categoryService.addCategoryService(categoryDto);

        return result;

    }

    @PutMapping
    public Result editCategory(@PathVariable Integer id,@RequestBody CategoryDto categoryDto){

        Result result = categoryService.editCategory(id, categoryDto);

        return result;

    }

    @GetMapping
    public List<Category> getCategories(){

        List<Category> categories = categoryService.getCategories();

        return categories;
    }


    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Integer id){

        Category category = categoryService.getCategory(id);

        return category;

    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){

        Result result = categoryService.deleteCategory(id);

        return result;

    }

}
