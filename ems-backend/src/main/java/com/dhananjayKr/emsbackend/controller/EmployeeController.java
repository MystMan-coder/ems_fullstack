package com.dhananjayKr.emsbackend.controller;

import com.dhananjayKr.emsbackend.dto.EmployeeDto;
import com.dhananjayKr.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto saveEmployeeDto = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(saveEmployeeDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employeeDto, @PathVariable("id") Long employeeId) {
        EmployeeDto updateEmployeeDto = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseEntity<>(updateEmployeeDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public String deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return String.format("Employee with id: %s deleted Successfully", employeeId);
    }
}
