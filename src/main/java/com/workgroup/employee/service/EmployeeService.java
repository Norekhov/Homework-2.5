package com.workgroup.employee.service;

import com.workgroup.employee.Employee.Employee;
import com.workgroup.employee.exception.EmployeeAlreadyAddedException;
import com.workgroup.employee.exception.EmployeeNotFoundException;
import com.workgroup.employee.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {
    private final int maxEmployees = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    public Employee add(String firstName, String lastName) {
        String key = key(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(key, employee);
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        String key = key(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.remove(key);
    }

    public Employee find(String firstName, String lastName) {
        String key = key(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        return employees.get(key);
    }

    public String key (String firstName, String lastName) {
        return firstName + lastName;
    }

    public List<Employee> findAll() {
        return List.copyOf(employees.values());
    }
}
