package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.payload.Result;
import com.example.demo.payload.UserDto;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){

        Result result = userService.addUser(userDto);
        return result;

    }

    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDto userDto){

        Result result = userService.editUser(id, userDto);
        return result;

    }

    @GetMapping
    public List<User> getUsers(){

        List<User> users = userService.getUsers();
        return users;
    }
    @GetMapping("/{id}")
    public User getUser(@PathVariable Integer id){

        User user = userService.getUser(id);
        return user;

    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){

        Result result = userService.deleteUser(id);
        return result;


    }


}
