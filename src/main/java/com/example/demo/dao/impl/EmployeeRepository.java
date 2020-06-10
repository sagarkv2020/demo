package com.example.demo.dao.impl;

import com.example.demo.dao.IEmployeeRepository;
import com.example.demo.model.EmployeeEntity;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeRepository implements IEmployeeRepository {

    @Override
    public List<EmployeeEntity> getAllEmployees() throws IOException, ParseException {
        URL url = new URL("http://dummy.restapiexample.com/api/v1/employees");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        Object obj = new JSONParser().parse(content.toString());
        JSONObject jo = (JSONObject) obj;

        String status = (String) jo.get("status");
        JSONArray data = (JSONArray) jo.get("data");

        System.out.println("status ==> " + status);
        System.out.println("data ==> " + data);

        return Arrays.asList(mapper.readValue(data.toJSONString(), EmployeeEntity[].class));
    }
}

