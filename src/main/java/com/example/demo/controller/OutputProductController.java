package com.example.demo.controller;

import com.example.demo.entity.OutputProduct;
import com.example.demo.payload.OutProductDto;
import com.example.demo.payload.Result;
import com.example.demo.service.OutputProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/outputproduct")
public class OutputProductController {


    @Autowired
    OutputProductService outputProductService;

    @PostMapping
    public Result addOutputProduct(@RequestBody OutProductDto outProductDto){

        Result result = outputProductService.addOutputProduct(outProductDto);
        return result;
    }

    @PutMapping("/{id}")
    public Result editOutputProduct(@PathVariable Integer id, @RequestBody OutProductDto outProductDto){

        Result result = outputProductService.editOutputProduct(id, outProductDto);
        return result;

    }

    @GetMapping
    public List<OutputProduct> getOutProducts(){

        List<OutputProduct> outProducts = outputProductService.getOutProducts();

        return outProducts;

    }

    @GetMapping("/{id}")
    public OutputProduct getOutPutProduct(@PathVariable Integer id){

        OutputProduct outProduct = outputProductService.getOutProduct(id);
        return outProduct;

    }

    @DeleteMapping("/{id}")
    public Result deleteOutProduct(@PathVariable Integer id){

        Result result = outputProductService.deleteOutputProduct(id);

        return result;

    }

}
