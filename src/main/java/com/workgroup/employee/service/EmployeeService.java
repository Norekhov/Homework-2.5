package com.workgroup.employee.service;

import com.workgroup.employee.exception.EmployeeAlreadyExistsException;
import com.workgroup.employee.exception.EmployeeNotFoundFullException;
import com.workgroup.employee.exception.EmployeeStorageIsFullException;
import com.workgroup.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final int max = 5;
    private final Map<String, Employee> employees = new HashMap<>();
    private final ValidationService validationService;

    public EmployeeService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public Employee add(String name, String surname, int salary, int department) {
        if (employees.size() >= max) {
            throw new EmployeeStorageIsFullException();
        }
        String key = buildKey(name, surname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyExistsException();
        }
        Employee employee = new Employee(
                validationService.validate(name),
                validationService.validate(surname),
                salary,
                department);
        employees.put(key, employee);
        return employee;
    }

    public Employee find(String name, String surname) {
        String key = buildKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundFullException();
        }
        return employees.get(key);
    }

    public Employee remove(String name, String surname) {
        String key = buildKey(name, surname);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundFullException();
        }
        return employees.remove(key);
    }

    public Collection<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    public String buildKey(String name, String surname) {
        return name + surname;
    }

}
