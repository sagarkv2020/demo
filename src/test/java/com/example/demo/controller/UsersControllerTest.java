package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.provider.MockUsersProvider;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

    @InjectMocks
    UsersController usersControllerUnderTest;

    @Mock
    UserService mockUserService;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(usersControllerUnderTest).build();
    }


    @Test
    public void testFindAll() {
        // Setup
        when(mockUserService.findAll()).thenReturn(MockUsersProvider.getAllUsers());

        // Run test
        final List<Users> users = usersControllerUnderTest.findAll();

        // verify results
        Assert.assertEquals(users.size(), 3);

    }

    @Test
    public void testFindByName() {
        // setup
        when(mockUserService.findByName(anyString())).thenReturn(MockUsersProvider.getAllUsers().get(0));

        // Run test
        final Users user = usersControllerUnderTest.findByName("AnyString");

        // verify results
        assert (user.getName().equals("Rajesh"));
    }

    @Test
    public void testLoad() throws Exception {

        // RequestBody Setup
        final Users user = MockUsersProvider.createUser();

        // setup & test post request
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    private String json(Users user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(user);
    }

    @Test
    public void testUpdate() throws Exception {
        // RequestBody Setup
        final Users user = MockUsersProvider.createUser();

        // setup & test post request
        mockMvc.perform(
                MockMvcRequestBuilders.put("/users/update")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json(user)))
                .andExpect(MockMvcResultMatchers.status().isOk());


    }


}
