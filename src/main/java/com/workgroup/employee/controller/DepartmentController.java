package com.workgroup.employee.controller;

import com.workgroup.employee.Employee.Employee;
import com.workgroup.employee.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee getMaxSalaryFromTheDepartment(@RequestParam Integer department) {
        return departmentService.getMaxSalaryFromTheDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee getMinSalaryFromTheDepartment(@RequestParam Integer department) {
        return departmentService.getMinSalaryFromTheDepartment(department);
    }

    @GetMapping(value = "/all", params = {"department"})
    public Collection<Employee> getEmployeesByDepartment(@RequestParam Integer department) {
        return departmentService.getEmployeesByDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAllEmployees() {
        return departmentService.getAllEmployees();
    }


}
