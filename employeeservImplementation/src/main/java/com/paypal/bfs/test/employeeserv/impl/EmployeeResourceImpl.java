package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.models.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        try {
            Employee employee =  employeeRepository.findById(Integer.parseInt(id)).get();
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            String message = "Employee detail not found with id: "+ id;
            LOGGER.info(message);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, message);
        } catch (Exception e){
            LOGGER.info("Failed to get employee detail with id %s : %s",id, e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error. Please try again later", e);
        }

    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        try {
            List<Employee> employeePresent = employeeRepository.findByInformation(employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth());
            Employee employeeSaved;
            if (employeePresent.isEmpty()) employeeSaved = employeeRepository.save(employee);
            else employeeSaved = employeePresent.get(0);
            return new ResponseEntity<>(employeeSaved, HttpStatus.OK);
        } catch (IllegalArgumentException | ConstraintViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error. Please try again later", e);
        }
    }
}
