package com.example.demo.controller;

import com.example.demo.entity.Measurement;
import com.example.demo.payload.Result;
import com.example.demo.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {

    @Autowired
    MeasurementService measurementService;

    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        Result measurementService = this.measurementService.addMeasurementService(measurement);

        return measurementService;

    }


    @GetMapping
    public List<Measurement> getMeasurements(){

        List<Measurement> measurements = measurementService.getMeasurements();
        return measurements;
    }

    @GetMapping("/{id}")
    public Measurement getMeasurement(@PathVariable Integer id){

        Measurement measurement = measurementService.getMeasurement(id);

        return measurement;


    }

    @PutMapping
    public Result editMeasurement(@PathVariable Integer id, @RequestBody Measurement measurement){

        Result result = measurementService.editMeasurement(id, measurement);

        return result;


    }


    @DeleteMapping
    public Result deleteMeasurement(@PathVariable Integer id){

        Result result = measurementService.deleteMeasurement(id);

        return result;


    }
}
