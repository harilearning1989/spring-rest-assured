package com.web.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.web.demo.dtos.EmpDataDto;
import com.web.demo.dtos.EmpDataMain;
import com.web.demo.models.EmployeeData;
import com.web.demo.repos.EmpDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmpDataServiceImpl implements EmpDataService {

    private EmpDataRepo empDataRepo;

    @Autowired
    public EmpDataServiceImpl setEmpDataRepo(EmpDataRepo empDataRepo) {
        this.empDataRepo = empDataRepo;
        return this;
    }

    @Override
    public List<EmployeeData> findAll() {
        return empDataRepo.findAll();
    }

    @Override
    public EmpDataDto createEmployee(EmpDataDto empDataDto) {
        EmployeeData employeeData = convertDtoTOModel(empDataDto);
        employeeData = empDataRepo.save(employeeData);
        EmpDataDto EmpDataDto = convertModelToDto(employeeData);
        return EmpDataDto;
    }

    @Override
    public Optional<EmployeeData> findById(int id) {
        return empDataRepo.findById(id);
    }

    @Override
    public boolean deleteEmpById(int id) {
        Optional<EmployeeData> employeeDataOpt = empDataRepo.findById(id);
        if (employeeDataOpt.isPresent()) {
            empDataRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void deleteAll() {
        empDataRepo.deleteAllEmployees();
    }

    @Override
    public List<EmpDataMain> readEmpDataJson() {
        List<EmpDataMain> empDataMainList = null;
        try {
            String fixture = Resources.toString(Resources.getResource("EmpData.json"), Charsets.UTF_8);
            ObjectMapper objectMapper = new ObjectMapper();
            empDataMainList = objectMapper.readValue(fixture,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, EmpDataMain.class));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return empDataMainList;
    }

    @Override
    public List<EmployeeData> saveAllEmployees() {
        List<EmpDataMain> empListMain = readEmpDataJson();
        List<EmpDataDto> empList = empListMain.get(0).getEmpDataDTO();
        List<EmployeeData> emplDataList = getEmployeeData(empList);

        return empDataRepo.saveAll(emplDataList);
    }

    @Override
    public EmpDataDto updateEmployee(int id) {
        Optional<EmployeeData> employeeDataOpt = empDataRepo.findById(id);
        if (employeeDataOpt.isPresent()) {
            EmployeeData employeeData = employeeDataOpt.get();
            employeeData = empDataRepo.save(employeeData);
            return convertModelToDto(employeeData);
        }
        return null;
    }

    private List<EmployeeData> getEmployeeData(List<EmpDataDto> empList) {
        return Optional.ofNullable(empList)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(m -> {
                    EmployeeData employeeData = convertDtoTOModel(m);
                    return employeeData;
                })
                .collect(Collectors.toList());
    }

    private EmployeeData convertDtoTOModel(EmpDataDto m) {
        EmployeeData employeeData = new EmployeeData();
        employeeData.setAge(m.getAge());
        employeeData.setDepartment(m.getDepartment());
        employeeData.setEmail(m.getEmail());
        employeeData.setFirstName(m.getFirstName());
        employeeData.setId(m.getId());
        employeeData.setGender(m.getGender());
        employeeData.setSalary(m.getSalary());
        employeeData.setLastName(m.getLastName());
        employeeData.setJobTitle(m.getJobTitle());
        employeeData.setYearsOfExperience(m.getYearsOfExperience());
        employeeData.setPhone(m.getPhone());

        return employeeData;
    }

    private EmpDataDto convertModelToDto(EmployeeData empData) {
        EmpDataDto empDataDto = new EmpDataDto();
        empDataDto.setAge(empData.getAge());
        empDataDto.setDepartment(empData.getDepartment());
        empDataDto.setEmail(empData.getEmail());
        empDataDto.setFirstName(empData.getFirstName());
        empDataDto.setId(empData.getId());
        empDataDto.setGender(empData.getGender());
        empDataDto.setSalary(empData.getSalary());
        empDataDto.setLastName(empData.getLastName());
        empDataDto.setJobTitle(empData.getJobTitle());
        empDataDto.setYearsOfExperience(empData.getYearsOfExperience());
        empDataDto.setPhone(empData.getPhone());
        return empDataDto;
    }

}
