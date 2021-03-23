package com.example.demo.service;

import com.example.demo.entity.Measurement;
import com.example.demo.payload.Result;
import com.example.demo.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;


    public Result addMeasurementService(Measurement measurement){

        boolean existsByName = measurementRepository.existsByName(measurement.getName());

        if (existsByName){

            return new Result("Such measurement already exist",false);

        }

        measurementRepository.save(measurement);

        return new Result("Measurement saved",true);


    }


    public List<Measurement> getMeasurements(){

        List<Measurement> measurementList = measurementRepository.findAll();

        return measurementList;
    }


    public Measurement getMeasurement(Integer id){

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);

        if (!optionalMeasurement.isPresent()){

            return new Measurement();

        }
        Measurement measurement = optionalMeasurement.get();

        return measurement;

    }


    public Result editMeasurement(Integer id, Measurement measurement){

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);

        if (!optionalMeasurement.isPresent()){

            return new Result("Such measurement not found",false);

        }
        Measurement measurement1 = optionalMeasurement.get();

        measurement1.setActive(measurement.isActive());
        measurement1.setId(measurement.getId());
        measurement1.setName(measurement.getName());
        measurementRepository.save(measurement1);

        return new Result("Measurement edited",true);


    }

    public Result deleteMeasurement(Integer id){

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);

        if (!optionalMeasurement.isPresent()){

            return new Result("Such measurement not found",false);

        }else {

            measurementRepository.deleteById(id);

            return new Result("Successfully deleted", true);
        }
    }


}
