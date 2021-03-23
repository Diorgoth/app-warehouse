package com.example.demo.controller;


import com.example.demo.entity.Supplier;
import com.example.demo.payload.Result;
import com.example.demo.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplier){

        Result result = supplierService.addProduct(supplier);

        return result;

    }

    @PutMapping("/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplier ){

        Result result = supplierService.editProduct(id, supplier);

        return result;

    }

    @GetMapping
    public List<Supplier> getSuppliers(){

        List<Supplier> getsuppliers = supplierService.getsuppliers();

        return getsuppliers;

    }

    @GetMapping("/{id}")
    public Supplier getSupplier(@PathVariable Integer id){

        Supplier supplier = supplierService.getSupplier(id);

        return supplier;

    }

    @DeleteMapping("/id")
    public Result deleteSupplier(@PathVariable Integer id){

        Result result = supplierService.deleteSupplier(id);

        return result;

    }

}
