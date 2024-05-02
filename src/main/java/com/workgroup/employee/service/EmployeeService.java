package com.workgroup.employee.service;

import com.workgroup.employee.Employee.Employee;
import com.workgroup.employee.exception.EmployeeAlreadyAddedException;
import com.workgroup.employee.exception.EmployeeNotFoundException;
import com.workgroup.employee.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService {
    private final int maxEmployees = 10;
    private final List<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() > maxEmployees) {
            throw new EmployeeStorageIsFullException();
        }
        employees.add((employee));
        return employee;
    }

    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        employees.remove(employee);
        return employee;
    }

    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    public List<Employee> findAll() {
        return Collections.unmodifiableList(employees);
    }
}
