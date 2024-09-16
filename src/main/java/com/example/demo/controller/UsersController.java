package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Api(value = "UsersCtrl", description = "Users API endpoints")
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Users> findAll() {
        return userService.findAll();
    }

    @GetMapping(value = "/{name}")
    public Users findByName(@PathVariable final String name) {
        return userService.findByName(name);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users load(@RequestBody final Users users) {
        userService.save(users);
        return userService.findByName(users.getName());
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Users update(@RequestBody final Users users) {
        return userService.updateUser(users);
    }

}
