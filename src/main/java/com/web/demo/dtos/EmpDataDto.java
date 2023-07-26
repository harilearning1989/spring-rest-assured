package com.web.demo.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "last_name",
        "email",
        "id",
        "age",
        "years_of_experience",
        "salary",
        "first_name",
        "gender",
        "phone",
        "job_title",
        "department"
})
public class EmpDataDto {

    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("age")
    private Integer age;
    @JsonProperty("years_of_experience")
    private Integer yearsOfExperience;
    @JsonProperty("salary")
    private Double salary;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("job_title")
    private String jobTitle;
    @JsonProperty("department")
    private String department;

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("age")
    public Integer getAge() {
        return age;
    }

    @JsonProperty("age")
    public void setAge(Integer age) {
        this.age = age;
    }

    @JsonProperty("years_of_experience")
    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    @JsonProperty("years_of_experience")
    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @JsonProperty("salary")
    public Double getSalary() {
        return salary;
    }

    @JsonProperty("salary")
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("job_title")
    public String getJobTitle() {
        return jobTitle;
    }

    @JsonProperty("job_title")
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String department) {
        this.department = department;
    }

}
