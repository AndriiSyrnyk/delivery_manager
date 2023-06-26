package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.employee_salary.EmployeeSalaryService;
import dodomu.deliverymanager.salary.Salary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeConverter {
    private final EmployeeSalaryService employeeSalaryService;

    public List<EmployeeDTO> convertEmployeeListToEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            final Salary currentSalary = employeeSalaryService
                    .findEmployeeSalariesByEmployeeAndDate(employee, new Date(System.currentTimeMillis()))
                    .getSalary();
            employeeDTOS.add(new EmployeeDTO(employee, currentSalary));
        }

        return employeeDTOS;
    }

    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee, employeeSalaryService
                .findEmployeeSalariesByEmployeeAndDate(employee, new Date(System.currentTimeMillis()))
                .getSalary());
    }
}
