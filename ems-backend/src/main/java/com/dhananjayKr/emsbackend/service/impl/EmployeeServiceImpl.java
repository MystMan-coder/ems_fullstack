package com.dhananjayKr.emsbackend.service.impl;

import com.dhananjayKr.emsbackend.dto.EmployeeDto;
import com.dhananjayKr.emsbackend.entity.Employee;
import com.dhananjayKr.emsbackend.exceptions.ResourceNotFoundException;
import com.dhananjayKr.emsbackend.mapper.EmployeeMapper;
import com.dhananjayKr.emsbackend.repository.EmployeeRepository;
import com.dhananjayKr.emsbackend.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employee = employeeRepository.findAll();
        List<EmployeeDto> employeeDtos = employee.stream().map(EmployeeMapper::mapToEmployeeDto).collect(Collectors.toList());
        return employeeDtos ;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id: %s does not exist", id))
        );

        return EmployeeMapper.mapToEmployeeDto(employee);
    }


    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id: %s does not exist", id))
        );

        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        return EmployeeMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(String.format("Employee with id: %s does not exist", id))
        );
        employeeRepository.delete(employee);
    }

}
