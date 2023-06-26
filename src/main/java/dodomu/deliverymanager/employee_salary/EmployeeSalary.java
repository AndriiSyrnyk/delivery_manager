package dodomu.deliverymanager.employee_salary;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.salary.Salary;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
public class EmployeeSalary {
    public EmployeeSalary(Employee employee, Salary salary, Date date) {
        this.id = new EmployeeSalaryId(employee.getId(), salary.getId(), date);
        this.employee = employee;
        this.salary = salary;
        this.date = date;
    }

    @EmbeddedId
    private EmployeeSalaryId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("salaryId")
    private Salary salary;

    @Column(insertable = false, updatable = false)
    private Date date;
}
