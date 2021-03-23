package com.example.demo.controller;

import com.example.demo.entity.Client;
import com.example.demo.payload.Result;
import com.example.demo.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClient(@RequestBody Client client){
        Result result = clientService.addClient(client);
        return result;
    }

    @PutMapping("/{id}")
    public Result editClient(@PathVariable Integer id,@RequestBody Client client){

        Result result = clientService.editClient(id, client);

        return result;

    }

    @GetMapping
    public List<Client> getClients(){

        List<Client> clients = clientService.getClients();

        return clients;

    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Integer id){

        Client client = clientService.getClient(id);

        return client;

    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id){

        Result result = clientService.deleteClient(id);

        return result;

    }


}
