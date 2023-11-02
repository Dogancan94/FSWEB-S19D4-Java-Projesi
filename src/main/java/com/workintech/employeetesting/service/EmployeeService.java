package com.workintech.employeetesting.service;

import com.workintech.employeetesting.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    Employee delete(int id);
    Employee findByEmail(String email);
    List<Employee> findBySalary(double salary);
    List<Employee> findByOrder();
}
