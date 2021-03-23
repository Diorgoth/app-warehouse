package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Warehouse;
import com.example.demo.payload.Result;
import com.example.demo.payload.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addUser(UserDto userDto){


        if (userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(),userDto.getLastName(),userDto.getPhoneNumber())){

            return new Result("Such User already exist",false);

        }

        User user = new User();

        user.setActive(userDto.isActive());
        user.setFirstName(userDto.getFirstName());
        user.setPassword(userDto.getPassword());
        user.setCode(userDto.getCode());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setLastName(userDto.getLastName());


        Set<Integer> warehouseIdList = userDto.getWarehouseSet();
        Set<Warehouse> warehouses = new HashSet<>();

        for (Integer warehouseId : warehouseIdList) {
            Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
            optionalWarehouse.ifPresent(warehouses::add);
        }
        user.setWarehouses(warehouses);

        userRepository.save(user);

        return new Result("User added",true);

    }

    public Result editUser(Integer id,UserDto userDto){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){

            if (userRepository.existsByFirstNameAndLastNameAndPhoneNumber(userDto.getFirstName(),userDto.getLastName(),userDto.getPhoneNumber())){

                return new Result("Such User already exist",false);

            }

            User user = optionalUser.get();

            user.setActive(userDto.isActive());
            user.setFirstName(userDto.getFirstName());
            user.setPassword(userDto.getPassword());
            user.setCode(userDto.getCode());
            user.setPhoneNumber(userDto.getPhoneNumber());
            user.setLastName(userDto.getLastName());


            Set<Integer> warehouseIdList = userDto.getWarehouseSet();
            Set<Warehouse> warehouses = new HashSet<>();

            for (Integer warehouseId : warehouseIdList) {
                Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
                optionalWarehouse.ifPresent(warehouses::add);
            }
            user.setWarehouses(warehouses);

            userRepository.save(user);

            return new Result("User edited",true);

        }
        return new Result("User not found",false);

    }


    public List<User> getUsers(){

        List<User> userList = userRepository.findAll();

        return userList;

    }


    public User getUser(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){

            return optionalUser.get();
        }else {
            return new User();
        }

    }


    public Result deleteUser(Integer id){

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){

            userRepository.deleteById(id);
            return new Result("User deleted",true);

        }else {

            return new Result("Such user not found",false);
        }

    }

}
