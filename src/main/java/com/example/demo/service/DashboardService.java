package com.example.demo.service;

import com.example.demo.DateUtil;
import com.example.demo.payload.DailyTotal;
import com.example.demo.repository.InputProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DashboardService {

    @Autowired
    InputProductRepository inputProductRepository;


    public DailyTotal inputProducts() {
        Date startOfDay = DateUtil.atStartOfDay(new Date());
        return inputProductRepository.findDailyInput(startOfDay);
    }


    public DailyTotal outputProducts() {
        Date startOfDay = DateUtil.atStartOfDay(new Date());
        return inputProductRepository.findDailyOutput(startOfDay);
    }

}
