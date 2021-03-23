package com.example.demo.controller;


import com.example.demo.entity.Output;
import com.example.demo.payload.OutputDto;
import com.example.demo.payload.Result;
import com.example.demo.service.OutputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/output")
public class OutputController {

    @Autowired
    OutputService outputService;

    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){

        Result result = outputService.addOutput(outputDto);
        return result;

    }
    @PutMapping("/{id}")
    public Result editOutput(@PathVariable Integer id, @RequestBody OutputDto outputDto){

        Result result = outputService.editOutput(id, outputDto);

        return result;

    }

    @GetMapping
    public List<Output> getOutputs(){

        List<Output> outputs = outputService.getOutputs();

        return outputs;
    }

    @GetMapping("/{id}")
    public Output getOutput(@PathVariable Integer id){

        Output output = outputService.getOutput(id);
        return output;

    }

    @DeleteMapping("/{id}")
    public Result deleteOutput(@PathVariable Integer id){

        Result result = outputService.deleteOutput(id);

        return result;

    }

}
