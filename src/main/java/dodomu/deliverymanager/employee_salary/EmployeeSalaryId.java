package dodomu.deliverymanager.employee_salary;

import jakarta.persistence.Embeddable;

@Embeddable
public class EmployeeSalaryId {
    private Integer employeeId;
    private Integer salaryId;
}
