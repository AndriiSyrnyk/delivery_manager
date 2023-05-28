package dodomu.deliverymanager.employee_salary;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.salary.Salary;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class EmployeeSalary {
//    @Column(name = "employee_id")
//    private int employeeId;
//
//    @Column(name = "salary_id")
//    private int salaryId;
    @EmbeddedId
    private EmployeeSalaryId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @ManyToOne
    @MapsId("salaryId")
    private Salary salary;

    private Date date;

//    @ManyToOne
//    @JoinColumn(name = "employee_id")
//    private Employee employee;
//
//    @ManyToOne
//    @JoinColumn(name = "salary_id")
//    private Salary salary;
}
