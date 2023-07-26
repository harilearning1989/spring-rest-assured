package com.web.demo.controls;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.models.Employee;
import com.web.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("emp")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public void setEmployeeService(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/helloworld")
    public ResponseEntity<String> helloWorld(){
        String message = employeeService.helloWorld();
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> findAll() {
        try {
            List<Employee> empList = employeeService.findAll();
            if (empList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<EmployeeDTO>> findAllUnderManager(@PathVariable("managerId") int managerId) {
        try {
            List<EmployeeDTO> empList = employeeService.findAllUnderManager(managerId);
            if (empList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmpById(@PathVariable("id") int empId) {
        Optional<Employee> empData = employeeService.findAllByEmpId(empId);

        if (empData.isPresent()) {
            return new ResponseEntity<>(empData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee _employee = employeeService.createEmployee(employee);
            return new ResponseEntity<>(_employee, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable("id") int empId) {
        Optional<Employee> empData = employeeService.findById(empId);
        if (empData.isPresent()) {
            Employee _employee = empData.get();
            return new ResponseEntity<>(employeeService.createEmployee(_employee), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") int id) {
        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            employeeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
