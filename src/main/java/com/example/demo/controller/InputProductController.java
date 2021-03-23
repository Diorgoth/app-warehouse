package com.example.demo.controller;

import com.example.demo.entity.InputProduct;
import com.example.demo.payload.InputProductDto;
import com.example.demo.payload.Result;
import com.example.demo.service.InputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inputproduct")
public class InputProductController {

    @Autowired
    InputProductService inputProductService;

     @PostMapping
     public Result addInputProduct(@RequestBody InputProductDto inputProductDto){

         Result result = inputProductService.addInputProduct(inputProductDto);

         return result;

     }

     @PutMapping("/{id}")
    public  Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){

         Result result = inputProductService.editInputProduct(id, inputProductDto);
         return result;

     }

     @GetMapping
     public List<InputProduct> getInputProducts(){

         List<InputProduct> inputProductList = inputProductService.getInputProducts();

         return inputProductList;
     }


     @GetMapping("/{id}")
     public InputProduct getInputProduct(@PathVariable Integer id){

         InputProduct inputProduct = inputProductService.getInputProduct(id);

         return inputProduct;

     }

     @DeleteMapping("/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){

         Result result = inputProductService.deleteInputProduct(id);
         return result;

     }


}
