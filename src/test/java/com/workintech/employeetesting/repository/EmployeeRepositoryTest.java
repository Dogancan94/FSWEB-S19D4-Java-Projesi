package com.workintech.employeetesting.repository;

import com.workintech.employeetesting.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee();
        employee1.setFirstName("Ali");
        employee1.setLastName("Veli");
        employee1.setEmail("ali@veli.com");
        employee1.setSalary(30000);

        Employee employee2 = new Employee();
        employee2.setFirstName("Ayse");
        employee2.setLastName("Ahmet");
        employee2.setEmail("ayse@ahmet.com");
        employee2.setSalary(40000);

        Employee employee3 = new Employee();
        employee3.setFirstName("Mahmut");
        employee3.setLastName("Yakup");
        employee3.setEmail("mahmut@yakup.com");
        employee3.setSalary(50000);

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        employeeRepository.saveAll(employeeList);
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @BeforeAll
    static void beforeAll() {

    }

    @AfterAll
    static void afterAll() {

    }

    @Test
    void findByEmail() {
        String nonExistingEmail = "dogan@test.com";
        Optional<Employee> employee = employeeRepository.findByEmail(nonExistingEmail);
        assertEquals(Optional.empty(), employee);

        String existingEmail = "ali@veli.com";
        Optional<Employee> existingEmployee = employeeRepository.findByEmail(existingEmail);
        assertNotNull(existingEmployee.get());
        assertEquals("Ali", existingEmployee.get().getFirstName());
        assertEquals(30000, existingEmployee.get().getSalary());
    }

    @Test
    void findBySalary() {
        List<Employee> employees = employeeRepository.findBySalary(35000);
        assertEquals(2, employees.size());
        assertEquals("Mahmut", employees.get(0).getFirstName());
        assertEquals("Ayse", employees.get(1).getFirstName());
    }

    @Test
    void findByOrder() {
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3, employees.size());
        assertEquals("Ayse", employees.get(0).getFirstName());
        assertEquals("Ali", employees.get(1).getFirstName());
        assertEquals("Mahmut", employees.get(2).getFirstName());
    }
}