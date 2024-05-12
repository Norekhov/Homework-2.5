package com.workgroup.employee.Employee;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;


    public Employee(String firstName, String lastName, int department, int salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.salary = salary;

    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public String getSurname() {
        return this.firstName;
    }

    public String getName() {
        return this.lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }
}
