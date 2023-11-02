package com.workintech.employeetesting.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.workintech.employeetesting.entity.Employee;
import com.workintech.employeetesting.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void save() throws Exception{
        Employee employee = new Employee();
        employee.setFirstName("Dogancan");
        employee.setLastName("Kinik");
        employee.setEmail("dk@test.com");
        employee.setSalary(20000);

        when(employeeService.save(employee)).thenReturn(employee);

        mockMvc.perform(post("/employee/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonToString(employee))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(20000));
    }

    @Test
    void findByOrder() throws Exception{
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName("Dogancan");
        employee.setLastName("Kinik");
        employee.setEmail("dk@test.com");
        employee.setSalary(20000);
        employeeList.add(employee);

        when(employeeService.findByOrder()).thenReturn(employeeList);

        mockMvc.perform(get("/employee/order"))
                .andExpect(status().isOk());
        verify(employeeService).findByOrder();
    }



    public static String asJsonToString(Object object){
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}