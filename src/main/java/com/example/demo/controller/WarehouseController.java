package com.example.demo.controller;

import com.example.demo.entity.Warehouse;
import com.example.demo.payload.Result;
import com.example.demo.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;

    @PostMapping
    public Result addWarehouse(@RequestBody Warehouse warehouse){

        Result result = warehouseService.addWarehouse(warehouse);

        return result;

    }

    @PutMapping("/{id}")
    public Result editWarehouse(@PathVariable Integer id,@RequestBody Warehouse warehouse){

        Result result = warehouseService.editWarehouse(id, warehouse);

        return result;
    }

    @GetMapping
    public List<Warehouse> getWarehouses(){

        List<Warehouse> warehouses = warehouseService.getWarehouses();

        return warehouses;

    }

    @GetMapping("/{id}")
    public Warehouse getWarehouse(@PathVariable Integer id){

        Warehouse warehouse = warehouseService.getWarehouse(id);

        return warehouse;

    }

    @DeleteMapping("/{id}")
    public Result deleteWarehouse(@PathVariable Integer id){

        Result result = warehouseService.deleteWarehouse(id);

        return result;
    }

}
