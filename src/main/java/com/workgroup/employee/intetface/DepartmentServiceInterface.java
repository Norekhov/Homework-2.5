package com.workgroup.employee.intetface;

import com.workgroup.employee.Employee.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentServiceInterface {
    Employee getMinSalaryFromTheDepartment(int department);

    Employee getMaxSalaryFromTheDepartment(int department);

    Collection<Employee> getEmployeesByDepartment(int department);

    Map<Integer, List<Employee>> getAllEmployees();
}
