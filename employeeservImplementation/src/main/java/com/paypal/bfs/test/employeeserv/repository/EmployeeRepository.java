package com.paypal.bfs.test.employeeserv.repository;

import com.paypal.bfs.test.employeeserv.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
        @Query(value = "SELECT * FROM employee WHERE first_name = :first_name and last_name = :last_name and date_of_birth = :dateOfBirth", nativeQuery = true)
        List<Employee> findByInformation(String first_name, String last_name, LocalDate dateOfBirth);
}
