package com.example.demo.service;

import com.example.demo.entity.Supplier;
import com.example.demo.payload.Result;
import com.example.demo.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;


    public Result addProduct(Supplier supplier){

        boolean existsByName = supplierRepository.existsByName(supplier.getName());

        if (existsByName){

            return new Result("Such supplier already exist",false);

        }

        Supplier supplier1 = new Supplier();

        supplier1.setActive(supplier.isActive());
        supplier1.setId(supplier.getId());
        supplier1.setName(supplier.getName());
        supplier1.setPhoneNumber(supplier.getPhoneNumber());

        supplierRepository.save(supplier1);
        return new Result("Successfully added",true);

    }


    public Result editProduct(Integer id, Supplier supplier){

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()){
            boolean existsByName = supplierRepository.existsByName(supplier.getName());

            if (existsByName){

                return new Result("Such supplier already exist",false);

            }

            Supplier supplier1 = optionalSupplier.get();

            supplier1.setActive(supplier.isActive());
            supplier1.setId(supplier.getId());
            supplier1.setName(supplier.getName());
            supplier1.setPhoneNumber(supplier.getPhoneNumber());

            supplierRepository.save(supplier1);
            return new Result("Successfully adited",true);


        }

        return new Result("Such Sapplier not found",false);

    }


    public List<Supplier> getsuppliers(){

        List<Supplier> supplierList = supplierRepository.findAll();

        return supplierList;

    }

    public Supplier getSupplier(Integer id){


        Optional<Supplier> supplier = supplierRepository.findById(id);

        if (supplier.isPresent()){

            return supplier.get();
        }else{

            return new Supplier();
        }


    }


    public Result deleteSupplier(Integer id){

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()){

            supplierRepository.deleteById(id);

            return new Result("Supplier deleted",true);

        }else {

            return new Result("Supplier not found",false);
        }

    }
}
