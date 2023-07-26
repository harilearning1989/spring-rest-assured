package com.web.demo.repos;

import com.web.demo.models.EmployeeData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpDataRepo extends JpaRepository<EmployeeData,Integer> {

    @Modifying
    @Query("delete from EmployeeData")
    void deleteAllEmployees();
}
