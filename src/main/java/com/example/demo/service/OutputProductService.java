package com.example.demo.service;

import com.example.demo.entity.Output;
import com.example.demo.entity.OutputProduct;
import com.example.demo.entity.Product;
import com.example.demo.payload.OutProductDto;
import com.example.demo.payload.OutputDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.OutputProductRepository;
import com.example.demo.repository.OutputRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OutputRepository outputRepository;


    public Result addOutputProduct(OutProductDto outProductDto){

        Optional<Product> optionalProduct = productRepository.findById(outProductDto.getProductId());

        if (!optionalProduct.isPresent()){

            return new Result("Such Product not found",false);

        }

        Optional<Output> optionalOutput = outputRepository.findById(outProductDto.getOutputId());

        if (!optionalOutput.isPresent()){

            return new Result("Such output not found",false);

        }

        OutputProduct outputProduct = new OutputProduct();

        outputProduct.setAmount(outProductDto.getAmount());
        outputProduct.setOutput(optionalOutput.get());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setPrice(outProductDto.getPrice());

        outputProductRepository.save(outputProduct);

        return new Result("OutputProduct added",true);

    }


    public Result editOutputProduct(Integer id, OutProductDto outProductDto){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (optionalOutputProduct.isPresent()){

            Optional<Product> optionalProduct = productRepository.findById(outProductDto.getProductId());

            if (!optionalProduct.isPresent()){

                return new Result("Such Product not found",false);

            }

            Optional<Output> optionalOutput = outputRepository.findById(outProductDto.getOutputId());

            if (!optionalOutput.isPresent()){

                return new Result("Such output not found",false);

            }

            OutputProduct outputProduct = new OutputProduct();

            outputProduct.setAmount(outProductDto.getAmount());
            outputProduct.setOutput(optionalOutput.get());
            outputProduct.setProduct(optionalProduct.get());
            outputProduct.setPrice(outProductDto.getPrice());

            outputProductRepository.save(outputProduct);

            return new Result("OutputProduct edited",true);

        }

        return new Result("Such outputproduct not found ",false);

    }

    public List<OutputProduct> getOutProducts(){
        List<OutputProduct> outputProductList = outputProductRepository.findAll();

        return outputProductList;
    }

    public OutputProduct getOutProduct(Integer id){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (optionalOutputProduct.isPresent()){

            return optionalOutputProduct.get();

        }else {
            return new OutputProduct();
        }

    }

    public Result deleteOutputProduct(Integer id){

        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);

        if (optionalOutputProduct.isPresent()){

            outputProductRepository.deleteById(id);

            return new Result("Output Product deleted",true);

        }else {

            return new Result("Such output not found",false);

        }

    }

}
