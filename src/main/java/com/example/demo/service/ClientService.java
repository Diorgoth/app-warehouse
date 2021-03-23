package com.example.demo.service;

import com.example.demo.entity.Client;
import com.example.demo.payload.Result;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client client){


        boolean existsByName = clientRepository.existsByName(client.getName());

        if (existsByName){

            return new Result("Such client already exist",false);

        }


        clientRepository.save(client);

        return new Result("Client added",true);

    }

    public Result editClient(Integer id,Client client){

        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()){


            Client client1 = optionalClient.get();

            client1.setActive(client.isActive());
            client1.setPhoneNumber(client.getPhoneNumber());
            client1.setName(client.getName());

            clientRepository.save(client1);

            return new Result("Client edited",true);

        }

        return new Result("Client not found",false);

    }


    public List<Client> getClients(){

        List<Client> clientList = clientRepository.findAll();

        return clientList;

    }

    public Client getClient(Integer id){

        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()){

            return optionalClient.get();


        }else {

            return new Client();

        }

    }

    public Result deleteClient(Integer id){

        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()){

            clientRepository.deleteById(id);
            return new Result("Client deleted",true);

        }else {

            return new Result("Client not found",false);
        }

    }





}
