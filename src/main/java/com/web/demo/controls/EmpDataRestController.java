package com.web.demo.controls;

import com.web.demo.dtos.EmpDataDto;
import com.web.demo.dtos.EmpDataMain;
import com.web.demo.models.EmployeeData;
import com.web.demo.services.EmpDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("empData")
public class EmpDataRestController {

    private EmpDataService empDataService;

    @Autowired
    public void setEmpDataService(EmpDataService empDataService) {
        this.empDataService = empDataService;
    }

    @GetMapping("/readJson")
    public ResponseEntity<List<EmpDataMain>> readEmpDataJson() {
        List<EmpDataMain> empDataMainList = empDataService.readEmpDataJson();
        return new ResponseEntity<>(empDataMainList, HttpStatus.OK);
    }

    @GetMapping("/saveAll")
    public ResponseEntity<List<EmployeeData>> saveAllEmployees() {
        try {
            List<EmployeeData> empList = empDataService.saveAllEmployees();
            if (empList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<EmployeeData>> findAll() {
        try {
            List<EmployeeData> empList = empDataService.findAll();
            if (empList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeData> findById(@PathVariable("id") int id) {
        Optional<EmployeeData> empData = empDataService.findById(id);
        if (empData.isPresent()) {
            return new ResponseEntity<>(empData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<EmpDataDto> createEmployee(@RequestBody EmpDataDto empDataDto) {
        try {
            EmpDataDto _empDataDto = empDataService.createEmployee(empDataDto);
            return new ResponseEntity<>(_empDataDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpDataDto> updateEmployee(
            @PathVariable("id") int id) {
        try {
            EmpDataDto _empDataDto = empDataService.updateEmployee(id);
            return new ResponseEntity<>(_empDataDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpById(@PathVariable("id") int id) {
        try {
            boolean status = empDataService.deleteEmpById(id);
            return new ResponseEntity<>(status,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllEmployees() {
        try {
            empDataService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
