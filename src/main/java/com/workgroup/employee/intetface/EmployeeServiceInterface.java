package com.workgroup.employee.intetface;

import com.workgroup.employee.Employee.Employee;

import java.util.Collection;

public interface EmployeeServiceInterface {

    Employee add(String firstName, String lastName, Integer department, Integer salary);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();


}
