package com.example.demo.controller;

import com.example.demo.entity.Currency;
import com.example.demo.payload.Result;
import com.example.demo.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

     @Autowired
     CurrencyService currencyService;

     @PostMapping
     public Result addCurrency(@RequestBody Currency currency){

          Result result = currencyService.addCurrency(currency);

          return result;
     }

     @PutMapping("/{id}")
     public Result editCurrency(@PathVariable Integer id,@RequestBody Currency currency){

          Result result = currencyService.editCurrency(id, currency);
          return result;

     }

     @GetMapping
     public List<Currency> getCurrencies(){

          List<Currency> currencyList = currencyService.getCurrencies();

          return currencyList;
     }

     @GetMapping("/{id}")
     public Currency getCurrency(@PathVariable Integer id){

          Currency currency = currencyService.getCurrency(id);

          return currency;

     }

     @DeleteMapping("/{id}")
     public Result deleteCurrency(@PathVariable Integer id){

          Result result = currencyService.deleteCurrency(id);

          return result;
     }


}
