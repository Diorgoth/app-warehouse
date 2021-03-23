package com.example.demo.controller;

import com.example.demo.entity.Input;
import com.example.demo.payload.InputDto;
import com.example.demo.payload.Result;
import com.example.demo.service.InputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/input")
public class InputController {


    @Autowired
    InputService inputService;

    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){

        Result result = inputService.addInput(inputDto);

        return result;

    }

    @PutMapping("/{id}")
    public Result editInput(@PathVariable Integer id, @RequestBody InputDto inputDto){

        Result result = inputService.editInput(id, inputDto);

        return result;
    }

    @GetMapping
    public List<Input> getInputs(){

        List<Input> inputs = inputService.getInputs();
        return inputs;

    }

    @GetMapping("/{id}")
    public Input getInput(@PathVariable Integer id){

        Input input = inputService.getInput(id);
        return input;

    }

    @DeleteMapping("/{id}")
    public Result deleteInput(@PathVariable Integer id){

        Result result = inputService.deleteInput(id);

        return result;
    }





}
