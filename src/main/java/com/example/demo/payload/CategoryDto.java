package com.example.demo.payload;

import lombok.Data;

@Data
public class CategoryDto {

    private  String name;

    private Integer parentCategoryId;

}
