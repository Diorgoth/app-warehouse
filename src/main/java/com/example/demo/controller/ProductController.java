package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.payload.ProductDto;
import com.example.demo.payload.Result;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        Result result = productService.addProduct(productDto);

        return result;

    }

    @PutMapping("/{id}")
    public Result editProduct(@PathVariable Integer id,@RequestBody ProductDto productDto){


        Result result = productService.editProduct(id, productDto);

        return result;

    }


    @GetMapping
    public List<Product> getProducts(){

        List<Product> products = productService.getProducts();

        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id){

        Product product = productService.getProduct(id);
        return product;

    }


    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id){


        Result result = productService.deleteProduct(id);

        return result;

    }
}
