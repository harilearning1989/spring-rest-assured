package com.web.demo.services;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.models.Employee;
import com.web.demo.repos.EmployeeRepo;
import com.web.demo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepo employeeRepo;

    @Autowired
    public void setEmployeeRepo(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Optional<Employee> findAllByEmpId(int empId) {
        return employeeRepo.findAllByEmpId(empId);
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Optional<Employee> findById(int empId) {
        return employeeRepo.findByEmpId(empId);
    }

    @Override
    public void deleteById(int id) {
        Optional<Employee> empOpt = employeeRepo.findById(id);
        if(empOpt.isPresent()){
            employeeRepo.deleteById(id);
        }else{
            throw new RuntimeException("Employee Not Found");
        }
    }

    @Override
    public void deleteAll() {
        employeeRepo.deleteAll();
    }

    @Override
    public List<EmployeeDTO> findAllUnderManager(int managerId) {
        List<Employee> allEmp = employeeRepo.findAll();
        return getEmployeesUnderManager(managerId, allEmp);
    }

    @Override
    public String helloWorld() {
        return "Hello World";
    }

    private List<EmployeeDTO> getEmployeesUnderManager(int managerId, List<Employee> allEmp) {
        return allEmp.stream()
                .filter(f -> f.getManager_id() == managerId)
                .map(m -> CommonUtils.convertToDto(m))
                .collect(Collectors.toList());
    }

}
