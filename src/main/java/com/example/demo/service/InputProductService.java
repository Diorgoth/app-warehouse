package com.example.demo.service;



import com.example.demo.entity.Input;
import com.example.demo.entity.InputProduct;
import com.example.demo.entity.Product;
import com.example.demo.payload.InputProductDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.InputProductRepository;
import com.example.demo.repository.InputRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputRepository inputRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    InputProductRepository inputProductRepository;

    public Result addInputProduct(InputProductDto inputProductDto){


        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());

        if (!optionalInput.isPresent()){

            return new Result("Such Input do not exist",false);

        }

        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());

        if (!optionalProduct.isPresent()){

            return new Result("Such Product do not exist",false);

        }

        InputProduct inputProduct = new InputProduct();

        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        inputProductRepository.save(inputProduct);

        return new Result("Inputproduct added",true);


    }

    public Result editInputProduct(Integer id, InputProductDto inputProductDto){

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);

        if (optionalInputProduct.isPresent()){

            Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());

            if (!optionalInput.isPresent()){

                return new Result("Such Input do not exist",false);

            }

            Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());

            if (!optionalProduct.isPresent()){

                return new Result("Such Product do not exist",false);

            }

            InputProduct inputProduct = optionalInputProduct.get();

            inputProduct.setProduct(optionalProduct.get());
            inputProduct.setInput(optionalInput.get());
            inputProduct.setAmount(inputProductDto.getAmount());
            inputProduct.setPrice(inputProductDto.getPrice());
            inputProduct.setExpireDate(inputProductDto.getExpireDate());

            inputProductRepository.save(inputProduct);

            return new Result("Inputproduct edited",true);



        }
        return new Result("Such Input Product not found",false);

    }

    public List<InputProduct> getInputProducts(){

        List<InputProduct> inputProductList = inputProductRepository.findAll();

        return inputProductList;
    }

    public InputProduct getInputProduct(Integer id){

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);

        if (optionalInputProduct.isPresent()){

            return optionalInputProduct.get();

        }else {
            return new InputProduct();
        }

    }

    public Result deleteInputProduct(Integer id){

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);

        if (optionalInputProduct.isPresent()){

            inputProductRepository.deleteById(id);

            return new Result("Input product deleted",true);

        }else {
            return new Result("Such Input product do not exist",false);
        }

    }



}
