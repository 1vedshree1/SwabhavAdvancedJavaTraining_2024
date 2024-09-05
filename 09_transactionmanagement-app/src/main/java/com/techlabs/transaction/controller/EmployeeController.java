package com.techlabs.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.techlabs.transaction.dto.EmployeeDto;
import com.techlabs.transaction.entity.Employee;
import com.techlabs.transaction.service.EmployeeService;

@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee(@RequestBody EmployeeDto employeeDto) {
       
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setSalary(employeeDto.getSalary());
       
        Employee savedEmployee = employeeService.addEmployees(employee, employeeDto.getAddress());
        return ResponseEntity.ok(savedEmployee);
    }
}
