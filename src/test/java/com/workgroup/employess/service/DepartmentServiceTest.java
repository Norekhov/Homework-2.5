package com.workgroup.employess.service;

import com.workgroup.employee.exception.EmployeeNotFoundFullException;
import com.workgroup.employee.model.Employee;
import com.workgroup.employee.service.DepartmentService;
import com.workgroup.employee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private final List<Employee> employees = List.of(
            new Employee("Артем", "Иванов", 40_000, 1),
            new Employee("Иван", "Петров", 50_000, 2),
            new Employee("Петр", "Сидоров", 60_000, 3),
            new Employee("Максим", "Морозов", 70_000, 1),
            new Employee("Александр", "Сидоркин", 80_000, 2),
            new Employee("Игорь", "Дубов", 90_000, 3)
    );

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    public void findEmployeesFromDepartmentTest() {
        assertThat(departmentService.findEmployeesFromDepartment(3))
                .containsExactlyInAnyOrder(
                        new Employee("Петр", "Сидоров", 60_000, 3),
                        new Employee("Игорь", "Дубов", 90_000, 3));

    }

    @Test
    public void sumFromDepartmentTest() {
        assertThat(departmentService.sumFromDepartment(1))
                .isEqualTo(110_000);

    }

    @Test
    public void maxSalaryFromDepartmentTest() {
        assertThat(departmentService.maxSalaryFromDepartment(2))
                .isEqualTo(80_000);

    }

    @Test
    public void maxSalaryFromDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundFullException.class)
                .isThrownBy(() -> departmentService.maxSalaryFromDepartment(4));

    }

    @Test
    public void minSalaryFromDepartmentTest() {
        assertThat(departmentService.minSalaryFromDepartment(3))
                .isEqualTo(60_000);

    }

    @Test
    public void minSalaryFromDepartmentNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundFullException.class)
                .isThrownBy(() -> departmentService.minSalaryFromDepartment(5));

    }

    @Test
    public void findEmployeesGroupByDepartmentTest() {
        assertThat(departmentService.findEmployeesGroupByDepartment())
                .containsExactlyInAnyOrderEntriesOf(Map.of(
                        1, List.of(new Employee("Артем", "Иванов", 40_000, 1), new Employee("Максим", "Морозов", 70_000, 1)),
                        2, List.of(new Employee("Иван", "Петров", 50_000, 2), new Employee("Александр", "Сидоркин", 80_000, 2)),
                        3, List.of(new Employee("Петр", "Сидоров", 60_000, 3), new Employee("Игорь", "Дубов", 90_000, 3))));


    }
}
