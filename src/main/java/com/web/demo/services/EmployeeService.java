package com.web.demo.services;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> findAll();

    Optional<Employee> findAllByEmpId(int empId);

    Employee createEmployee(Employee employee);

    Optional<Employee> findById(int empId);

    void deleteById(int id);

    void deleteAll();

    List<EmployeeDTO> findAllUnderManager(int managerId);

    String helloWorld();
}
