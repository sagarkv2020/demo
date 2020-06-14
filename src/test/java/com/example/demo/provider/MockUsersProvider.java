package com.example.demo.provider;

import com.example.demo.controller.UsersControllerTest;
import com.example.demo.model.Users;

import java.util.Arrays;
import java.util.List;

public class MockUsersProvider {

    public static List<Users> getAllUsers() {
        return Arrays.asList(
                new Users(1111, "Rajesh", "Credit-Suisse", 5000),
                new Users(2222, "Aloke", "Credit-Suisse", 6000),
                new Users(3333, "Sagar", "Credit-Suisse", 7000)
        );
    }

    public static Users createUser() {
        return new Users(1L, "DemoUserAdded", "Credit-Suisse", 1000);
    }
}
