package com.demoproject.springboot.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/emps")
public class empController {

    @GetMapping
    public List<JsonNode> getEmployees() throws IOException {
        // Load the JSON file from the classpath
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/emp.json");

        if (inputStream == null) {
            throw new IOException("Static JSON file not found");
        }

        // Parse the JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(inputStream);

        // Return the "employees" array as a list
        return objectMapper.convertValue(rootNode.get("employees"), List.class);
    }
}
