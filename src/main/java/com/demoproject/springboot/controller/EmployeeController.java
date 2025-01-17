package com.demoproject.springboot.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final List<JsonNode> employees = new ArrayList<>();

    public EmployeeController() throws IOException {
        // Load the JSON file from the classpath
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("static/employee.json");

        if (inputStream == null) {
            throw new IOException("Static JSON file not found");
        }

        // Parse the JSON file into a JsonNode
        JsonNode rootNode = objectMapper.readTree(inputStream);

        // Initialize the in-memory employee list
        ArrayNode employeeArray = (ArrayNode) rootNode.get("static/employee.json");
        if (employeeArray != null) {
           employees.addAll((Collection<? extends JsonNode>) employeeArray);
          //  employees.addAll(employeeArray);
        }
    }

    @GetMapping
    public List<JsonNode> getEmployees() {
        return employees;
    }

    @PostMapping
    public ResponseEntity<JsonNode> addEmployee(@RequestBody ObjectNode newEmployee) {
        // Validate the input
        if (!newEmployee.has("name") || !newEmployee.has("age") || !newEmployee.has("salary")) {
            return ResponseEntity.badRequest().body(null);
        }

        // Add the new employee to the in-memory list
        employees.add(newEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEmployee);
    }
}

