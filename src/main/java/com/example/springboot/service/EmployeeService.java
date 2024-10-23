package com.example.springboot.service;

import com.example.springboot.entity.Employee;
import com.example.springboot.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer is where all the business logic lies
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent())
            return optionalEmployee.get();
        log.info("Employee with id={} doesn't exist.", id);
        return null;
    }

    public Employee saveEmployee(Employee employee) {
        final var now = LocalDateTime.now();
        employee.setCreatedAt(now);
        employee.setUpdatedAt(now);
        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee with id={} saved successfully", employee.getId());
        return savedEmployee;
    }

    public Employee updateEmployee(Employee e) {
        Optional<Employee> existingEmployee = employeeRepository.findById(e.getId());
        e.setCreatedAt(existingEmployee.get().getCreatedAt());
        e.setUpdatedAt(LocalDateTime.now());

        Employee updatedEmployee = employeeRepository.save(e);

        log.info("Employee with id={} updated successfully.", e.getId());
        return updatedEmployee;
    }

    public void deleteEmployeeById(Integer id) {
        employeeRepository.deleteById(id);
    }

    private final EmployeeRepository employeeRepository;
}
