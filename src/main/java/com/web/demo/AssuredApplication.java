package com.web.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.models.Employee;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.utils.CommonUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
public class AssuredApplication {

    //https://api.slingacademy.com/v1/sample-data/files/employees.json
    public static void main(String[] args) {
        SpringApplication.run(AssuredApplication.class, args);
    }

    private EmployeeRepo employeeRepo;

    @Autowired
    public AssuredApplication setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
        return this;
    }

    @PostConstruct
    public void saveEmployee() {
        List<EmployeeDTO> employeeDTOList = null;
        try {
            String fixture = Resources.toString(Resources.getResource("employee.json"), Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            employeeDTOList = objectMapper.readValue(fixture,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, EmployeeDTO.class));
            List<Employee> employeeList = getEmployeeList(employeeDTOList);

            employeeRepo.saveAll(employeeList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Employee> getEmployeeList(List<EmployeeDTO> employeeDTOList) {
        return Optional.ofNullable(employeeDTOList)
                .orElseGet(Collections::emptyList)
                .stream()
                .filter(Objects::nonNull)
                .map(m -> {
                    Employee emp = CommonUtils.convertDtoToModel(m);
                    return emp;
                })
                .collect(Collectors.toList());
    }

}
