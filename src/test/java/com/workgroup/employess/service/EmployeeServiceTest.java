package com.workgroup.employess.service;

import com.workgroup.employee.exception.EmployeeAlreadyExistsException;
import com.workgroup.employee.exception.EmployeeNotFoundFullException;
import com.workgroup.employee.exception.EmployeeStorageIsFullException;
import com.workgroup.employee.model.Employee;
import com.workgroup.employee.service.EmployeeService;
import com.workgroup.employee.service.ValidationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidationService());
    private final List<Employee> employees = List.of(
            new Employee("Артем", "Иванов", 40_000, 1),
            new Employee("Иван", "Петров", 50_000, 2),
            new Employee("Петр", "Сидоров", 60_000, 3)
    );

    @BeforeEach
    public void beforeEach() {
        employees.forEach(employee -> employeeService.add(employee.getName(), employee.getSurname(), employee.getSalary(), employee.getDepartment()));
    }

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getName(), employee.getSurname()));
    }


    @Test
    public void addTest() {
        Employee expected = new Employee("Андрей", "Петров", 45_000, 3);

        Employee actual = employeeService.add("Андрей", "Петров", 45_000, 3);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Андрей", "Петров"));
        assertThat(actual).isIn(employeeService.findAll());
    }

    @Test
    public void addNegativeTest() {
        employeeService.add("Андрей", "Петров", 45_000, 3);
        employeeService.add("Петр", "Андреев", 55_000, 2);

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Максим", "Зачиналов", 80_000, 1));
    }

    @Test
    public void addSecondNegativeTest() {
        assertThatExceptionOfType(EmployeeAlreadyExistsException.class)
                .isThrownBy(() -> employeeService.add("Иван", "Петров", 50_000, 2));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Артем", "Иванов", 40_000, 1);

        Employee actual = employeeService.find("Артем", "Иванов");

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundFullException.class)
                .isThrownBy(() -> employeeService.find("Максим", "Зачиналов"));
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Артем", "Иванов", 40_000, 1);

        Employee actual = employeeService.remove("Артем", "Иванов");

        assertThat(actual).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundFullException.class)
                .isThrownBy(() -> employeeService.find("Артем", "Иванов"));
        assertThat(actual).isNotIn(employeeService.findAll());
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundFullException.class)
                .isThrownBy(() -> employeeService.remove("Максим", "Зачиналов"));
    }

    @Test
    public void findAllTest() {
        assertThat(employeeService.findAll())
                .containsExactlyInAnyOrderElementsOf(employees);
    }



}
