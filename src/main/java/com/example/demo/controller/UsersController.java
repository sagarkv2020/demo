package com.example.demo.controller;

import com.example.demo.dao.UserRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PutMapping("/update")
    public Users update(@RequestBody final Users users) {
        Optional<Users> usersOptional = userRepository.findById(users.getId());
        Users user;
        if (usersOptional.isPresent()) {
            user = usersOptional.get();
            // Setting Details
            user.setName(users.getName());
            user.setSalary(users.getSalary());
            user.setTeamName(users.getTeamName());
            userRepository.save(users);
            return user;
        } else {
            throw new ResourceNotFoundException("Resource with id : " + users.getId() + " Not Found");
        }
    }

}
