package com.example.demo.service;


import com.example.demo.entity.Client;
import com.example.demo.entity.Currency;
import com.example.demo.entity.Output;
import com.example.demo.entity.Warehouse;
import com.example.demo.payload.OutputDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.CurrencyRepository;
import com.example.demo.repository.OutputRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;
    @Autowired
    CurrencyRepository currencyRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addOutput(OutputDto outputDto){

        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());

        if (!optionalClient.isPresent()){

            return new Result("Clien not found",false);

        }

        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());

        if (!optionalCurrency.isPresent()){

            return new Result("Currency not found",false);

        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());

        if (!optionalWarehouse.isPresent()){

            return new Result("Warehouse not found",false);

        }

        Output output = new Output();

        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setCode(outputDto.getCode());
        output.setDate(outputDto.getDate());
        output.setWarehouse(optionalWarehouse.get());
        output.setFactureNumber(outputDto.getFactureNumber());

        outputRepository.save(output);

        return new Result("Output added",true);


    }

    public Result editOutput(Integer id,OutputDto outputDto){

        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (optionalOutput.isPresent()){

            Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());

            if (!optionalClient.isPresent()){

                return new Result("Clien not found",false);

            }

            Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());

            if (!optionalCurrency.isPresent()){

                return new Result("Currency not found",false);

            }

            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());

            if (!optionalWarehouse.isPresent()){

                return new Result("Warehouse not found",false);

            }

            Output output = optionalOutput.get();

            output.setClient(optionalClient.get());
            output.setCurrency(optionalCurrency.get());
            output.setCode(outputDto.getCode());
            output.setDate(outputDto.getDate());
            output.setWarehouse(optionalWarehouse.get());
            output.setFactureNumber(outputDto.getFactureNumber());

            outputRepository.save(output);

            return new Result("Output edited",true);

        }

        return new Result("Such output not found",false);

    }


    public List<Output> getOutputs(){

        List<Output> outputList = outputRepository.findAll();

        return outputList;

    }

    public Output getOutput(Integer id){

        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (optionalOutput.isPresent()){

            return optionalOutput.get();

        }else {

            return new Output();
        }

    }

    public Result deleteOutput(Integer id){

        Optional<Output> optionalOutput = outputRepository.findById(id);

        if (optionalOutput.isPresent()) {

            outputRepository.deleteById(id);
            return new Result("Output Deleted",true);
        }else {

            return new Result("Such Output not found",false);
        }

    }



}
