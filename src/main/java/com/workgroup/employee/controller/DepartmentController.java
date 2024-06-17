package com.workgroup.employee.controller;

import com.workgroup.employee.model.Employee;
import com.workgroup.employee.service.DepartmentService;
import com.workgroup.employee.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
@GetMapping("/{id}/employees")
    public List<Employee> findEmployeesFromDepartment(@PathVariable("id") int department) {
        return departmentService.findEmployeesFromDepartment(department);
    }

    @GetMapping("/{id}/salary/sum")
    public int sumFromDepartment(@PathVariable("id") int department) {
        return departmentService.sumFromDepartment(department);
    }

    @GetMapping("/{id}/salary/max")
    public int maxSalaryFromDepartment(@PathVariable("id") int department) {
        return departmentService.maxSalaryFromDepartment(department);
    }

    @GetMapping("/{id}/salary/min")
    public int minSalaryFromDepartment(@PathVariable("id") int department) {
        return departmentService.minSalaryFromDepartment(department);
    }

    @GetMapping("/employees")
    public Map<Integer,List<Employee>> findEmployeesGroupByDepartment() {
        return departmentService.findEmployeesGroupByDepartment();
    }
}


