package com.example.demo.service;

import com.example.demo.entity.Currency;
import com.example.demo.payload.Result;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    CurrencyRepository currencyRepository;


    public Result addCurrency(Currency currency){

        boolean existsByName = currencyRepository.existsByName(currency.getName());

        if (existsByName){
            return new Result("Such Currency already exist",false);
        }

        Currency currency1 = new Currency();

        currency1.setId(currency.getId());
        currency1.setActive(currency.isActive());
        currency1.setName(currency.getName());

        currencyRepository.save(currency1);

        return new Result("Currency added",true);


    }

    public Result editCurrency(Integer id, Currency currency){

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);

        if (optionalCurrency.isPresent()){

            boolean existsByName = currencyRepository.existsByName(currency.getName());

            if (existsByName){
                return new Result("Such Currency already exist",false);
            }

            Currency currency1 = optionalCurrency.get();

            currency1.setId(currency.getId());
            currency1.setActive(currency.isActive());
            currency1.setName(currency.getName());

            currencyRepository.save(currency1);

            return new Result("Currency adited",true);


        }

        return new Result("Such Currency not found",false);

    }

    public List<Currency> getCurrencies(){

        List<Currency> currencyList = currencyRepository.findAll();

        return currencyList;
    }

    public Currency getCurrency(Integer id){

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);

        if (optionalCurrency.isPresent()){

            return optionalCurrency.get();
        }else {
            return new Currency();
        }

    }

    public Result deleteCurrency(Integer id){

        Optional<Currency> optionalCurrency = currencyRepository.findById(id);

        if (optionalCurrency.isPresent()){

            currencyRepository.deleteById(id);

            return new Result("Currency deleted",true);
        }else {

            return new Result("Such currency not found",false);
        }

    }
}
