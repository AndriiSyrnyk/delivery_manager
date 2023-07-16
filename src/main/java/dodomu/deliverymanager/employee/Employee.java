package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.delivery.Delivery;
import dodomu.deliverymanager.employee_salary.EmployeeSalary;
import dodomu.deliverymanager.schedule.Schedule;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String comment;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    private List<EmployeeSalary> employeeSalaries;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "employee")
    private List<Schedule> employeeSchedules;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private List<Delivery> deliveries;
}