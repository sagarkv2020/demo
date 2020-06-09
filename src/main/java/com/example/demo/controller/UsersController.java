package com.example.demo.controller;

import com.example.demo.model.entity.Users;
import com.example.demo.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{name}")
    public Users findByName(@PathVariable final String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/add")
    public Users load(@RequestBody final Users users) {
        userRepository.save(users);
        return userRepository.findByName(users.getName());
    }

    /*@PutMapping("/update")
    public Users update(@RequestBody final Users users) {
        userRepository.up
    }*/

}
