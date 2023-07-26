package com.web.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total_employees",
        "message",
        "employees"
})
public class EmpDataMain {

    @JsonProperty("total_employees")
    private Integer totalEmployees;
    @JsonProperty("message")
    private String message;
    @JsonProperty("employees")
    private List<EmpDataDto> employees;

    @JsonProperty("total_employees")
    public Integer getTotalEmployees() {
        return totalEmployees;
    }

    @JsonProperty("total_employees")
    public void setTotalEmployees(Integer totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("employees")
    public List<EmpDataDto> getEmpDataDTO() {
        return employees;
    }

    @JsonProperty("employees")
    public void setEmpDataDTO(List<EmpDataDto> employees) {
        this.employees = employees;
    }

}
