package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.models.Employee;
import com.paypal.bfs.test.employeeserv.repository.EmployeeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

public class EmployeeResourceImplTest {
    @Mock
    EmployeeRepository employeeRepository;
    EmployeeResourceImpl employeeResourceImpl;

    public EmployeeResourceImplTest() {
        MockitoAnnotations.initMocks(this);
        employeeResourceImpl = new EmployeeResourceImpl();
        employeeResourceImpl.employeeRepository = employeeRepository;
    }

    @Test
    public void createEmployeeSuccess() {
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(new Employee());
        ResponseEntity res = employeeResourceImpl.createEmployee(new Employee());
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResponseStatusException.class)
    public void createEmployeeValidationFailure() throws Exception{
        Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenThrow(ConstraintViolationException.class);
        ResponseEntity res = employeeResourceImpl.createEmployee(new Employee());
    }

    @Test
    public void employeeGetByIdSuccessStep() {
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(java.util.Optional.of(new Employee()));
        ResponseEntity res = employeeResourceImpl.employeeGetById("1");
        Assert.assertEquals(res.getStatusCode(), HttpStatus.OK);
    }

    @Test(expected = ResponseStatusException.class)
    public void employeeGetByIdNotFound() {
        Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenThrow(NoSuchElementException.class);
        employeeResourceImpl.employeeGetById("1");
    }
}
