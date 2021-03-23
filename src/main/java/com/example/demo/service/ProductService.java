package com.example.demo.service;

import com.example.demo.entity.Attachment;
import com.example.demo.entity.Category;
import com.example.demo.entity.Measurement;
import com.example.demo.entity.Product;
import com.example.demo.payload.ProductDto;
import com.example.demo.payload.Result;
import com.example.demo.repository.AttachmentRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.MeasurementRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {

        boolean byNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());

        if (byNameAndCategoryId) {

            return new Result("Such product already exist", false);

        }

        //
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        if (!optionalCategory.isPresent()) {

            return new Result("Such category do not exist", false);

        }

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());

        if (!optionalAttachment.isPresent()) {

            return new Result("Such photo do not exist", false);

        }

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());

        if (!optionalMeasurement.isPresent()) {

            return new Result("Such measurement do not exist", false);

        }

        Product product = new Product();

        product.setName(productDto.getName());
        product.setCode(generateUUIDCode());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);

        return new Result("Product saved", true);


    }

    public Result editProduct(Integer id, ProductDto productDto){

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()){
            boolean byNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());

            if (byNameAndCategoryId) {

                return new Result("Such product already exist", false);

            }

            //
            Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

            if (!optionalCategory.isPresent()) {

                return new Result("Such category do not exist", false);

            }

            Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());

            if (!optionalAttachment.isPresent()) {

                return new Result("Such photo do not exist", false);

            }

            Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());

            if (!optionalMeasurement.isPresent()) {

                return new Result("Such measurement do not exist", false);

            }

            Product product = optionalProduct.get();

            product.setName(productDto.getName());
            product.setCode(generateUUIDCode());
            product.setCategory(optionalCategory.get());
            product.setPhoto(optionalAttachment.get());
            product.setMeasurement(optionalMeasurement.get());
            productRepository.save(product);

            return new Result("Product edited", true);

        }
        return new Result("Such product not found",false);

    }


    public List<Product> getProducts(){

        List<Product> productList = productRepository.findAll();

        return productList;

    }

    public Product getProduct(Integer id){

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (!optionalProduct.isPresent()){

            return new Product();

        }else {

            return optionalProduct.get();
        }


    }


    public Result deleteProduct(Integer id){

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()){

            productRepository.deleteById(id);
            return new Result("Product deleted",true);

        }
        else{

            return new Result("Such product not found",false);

        }

    }
























    public static String generateUUIDCode() {

        return UUID.randomUUID().toString();
    }


}



