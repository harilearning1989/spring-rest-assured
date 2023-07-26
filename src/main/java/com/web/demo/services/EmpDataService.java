package com.web.demo.services;

import com.web.demo.dtos.EmpDataDto;
import com.web.demo.dtos.EmpDataMain;
import com.web.demo.models.EmployeeData;

import java.util.List;
import java.util.Optional;

public interface EmpDataService {
    List<EmployeeData> findAll();

    EmpDataDto createEmployee(EmpDataDto empDataDto);

    Optional<EmployeeData> findById(int empId);

    boolean deleteEmpById(int id);

    void deleteAll();

    List<EmpDataMain> readEmpDataJson();

    List<EmployeeData> saveAllEmployees();

    EmpDataDto updateEmployee(int id);
}
