package com.example.demo.service;

import com.example.demo.DateUtil;
import com.example.demo.constants.ExpireStatus;
import com.example.demo.entity.InputProduct;
import com.example.demo.repository.InputProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

public class ExpirecheckService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Scheduled(cron = "0 9 * * ?")
    void checkWarningStatus(){

        Date date = DateUtil.getDateFromFuture(10);
        List<InputProduct> inputProductList = inputProductRepository.getWarningProducts(date);

        for (InputProduct inputProduct: inputProductList) {
            inputProduct.setExpireStatus(ExpireStatus.WARNING);
            inputProductRepository.save(inputProduct);
        }

    }

}
