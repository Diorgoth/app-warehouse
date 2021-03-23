package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.entity.Input;
import com.example.demo.entity.Supplier;
import com.example.demo.entity.Warehouse;
import com.example.demo.payload.InputDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.InputRepository;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    WarehouseRepository warehouseRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    SupplierRepository supplierRepository;



    public Result addInput(InputDto inputDto){

        Input input = new Input();

        input.setCode(inputDto.getCode());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setDate(inputDto.getDate());

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());

        if (!optionalWarehouse.isPresent()){

            return new Result("Warehouse not found",false);

        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());

        if (!optionalCurrency.isPresent()){

            return new Result("Currency not found",false);

        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());

        if (!optionalSupplier.isPresent()){

            return new Result("Supplier not found",false);

        }

        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());

        inputRepository.save(input);

        return new Result("Input added",true);


    }

    public Result editInput(Integer id, InputDto inputDto){

        Optional<Input> optionalInput = inputRepository.findById(id);

        if (optionalInput.isPresent()){

            Input input = optionalInput.get();

            input.setCode(inputDto.getCode());
            input.setFactureNumber(inputDto.getFactureNumber());
            input.setDate(inputDto.getDate());

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());

            if (!optionalWarehouse.isPresent()){

                return new Result("Warehouse not found",false);

            }

            Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());

            if (!optionalCurrency.isPresent()){

                return new Result("Currency not found",false);

            }

            Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());

            if (!optionalSupplier.isPresent()){

                return new Result("Supplier not found",false);

            }

            input.setCurrency(optionalCurrency.get());
            input.setSupplier(optionalSupplier.get());
            input.setWarehouse(optionalWarehouse.get());

            inputRepository.save(input);

            return new Result("Input edited",true);




        }

        return new Result("Such Input not found",false);

    }

    public List<Input> getInputs(){

        List<Input> inputs = inputRepository.findAll();

        return inputs;

    }

    public Input getInput(Integer id){

        Optional<Input> optionalInput = inputRepository.findById(id);

        if (optionalInput.isPresent()){

            return optionalInput.get();

        }else {
            return new Input();
        }

    }

    public Result deleteInput(Integer id){

        Optional<Input> optionalInput = inputRepository.findById(id);

        if (optionalInput.isPresent()){

            inputRepository.deleteById(id);

            return new Result("Input deleted",true);

        }else {

            return new Result("Input not found",false);

        }
    }



}
