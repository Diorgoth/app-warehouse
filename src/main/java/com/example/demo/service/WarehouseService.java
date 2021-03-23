package com.example.demo.service;

import com.example.demo.entity.Warehouse;
import com.example.demo.payload.Result;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addWarehouse(Warehouse warehouse){

        Warehouse warehouse1 = new Warehouse();

        warehouse1.setActive(warehouse.isActive());
        warehouse1.setId(warehouse.getId());
        warehouse1.setName(warehouse.getName());

        warehouseRepository.save(warehouse1);
        return new Result("Warehouse added",true);
    }

    public Result editWarehouse(Integer id, Warehouse warehouse){

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        if (optionalWarehouse.isPresent()){

            Warehouse warehouse1 = optionalWarehouse.get();

            warehouse1.setActive(warehouse.isActive());
            warehouse1.setId(warehouse.getId());
            warehouse1.setName(warehouse.getName());

            warehouseRepository.save(warehouse1);
            return new Result("Warehouse adited",true);

        }

        return new Result("Such Warehouse not found",false);

    }


    public List<Warehouse> getWarehouses(){

        List<Warehouse> warehouseList = warehouseRepository.findAll();

        return warehouseList;

    }

    public Warehouse getWarehouse(Integer id){


        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        return optionalWarehouse.orElseGet(Warehouse::new);
    }

    public Result deleteWarehouse(Integer id){

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);

        if (optionalWarehouse.isPresent()){

            warehouseRepository.deleteById(id);
            return new Result("Warehouse deleted",true);

        }else{

return new Result("Such warehouse not found",false);

        }

    }
}
