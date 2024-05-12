package com.workgroup.employee.service;

import com.workgroup.employee.Employee.Employee;
import com.workgroup.employee.exception.EmployeeNotFoundException;
import com.workgroup.employee.intetface.DepartmentServiceInterface;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getMinSalaryFromTheDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getMaxSalaryFromTheDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getEmployeesByDepartment(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployees() {
        return employeeService.findAll().stream()
                .collect(groupingBy(Employee::getDepartment));
    }
}
