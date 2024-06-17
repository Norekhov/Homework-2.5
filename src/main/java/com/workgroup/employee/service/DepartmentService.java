package com.workgroup.employee.service;

import com.workgroup.employee.exception.EmployeeNotFoundFullException;
import com.workgroup.employee.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public List<Employee> findEmployeesFromDepartment (int department) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public int sumFromDepartment(int department) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }
    public int maxSalaryFromDepartment (int department) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundFullException::new);
    }
    public int minSalaryFromDepartment (int department) {
        return employeeService.findAll()
                .stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundFullException::new);
    }
    public Map<Integer,List<Employee>> findEmployeesGroupByDepartment() {
        return employeeService.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
