package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<Users> findAll() {
        return userRepository.findAll();
    }

    public Users findByName(String name) {
        return userRepository.findByName(name);
    }

    public Users save(Users users) {
        return userRepository.save(users);
    }

    public Users updateUser(Users users) {
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
