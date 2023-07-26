package com.web.demo.utils;

import com.web.demo.dtos.EmployeeDTO;
import com.web.demo.models.Employee;

public class CommonUtils {

    public static EmployeeDTO convertToDto(Employee m) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(m.getId());
        dto.setEmpId(m.getEmpId());
        dto.setEmpName(m.getEmpName());
        dto.setFatherName(m.getFatherName());
        dto.setCategory(m.getCategory());
        dto.setGender(m.getGender());
        dto.setMobile(m.getMobile());
        dto.setManager_id(m.getManager_id());
        dto.setSalary(m.getSalary());
        dto.setDesignation(m.getDesignation());

        return dto;
    }

    public static Employee convertDtoToModel(EmployeeDTO dto) {
        Employee emp =new Employee();
        emp.setId(dto.getId());
        emp.setEmpName(dto.getEmpName());
        emp.setFatherName(dto.getFatherName());
        emp.setManager_id(dto.getManager_id());
        emp.setDesignation(dto.getDesignation());
        emp.setCategory(dto.getCategory());
        emp.setEmpId(dto.getEmpId());
        emp.setGender(dto.getGender());
        emp.setSalary(dto.getSalary());
        emp.setMobile(dto.getMobile());

        return emp;
    }
}
