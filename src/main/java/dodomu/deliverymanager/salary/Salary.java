package dodomu.deliverymanager.salary;

import dodomu.deliverymanager.employee_salary.EmployeeSalary;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "salary_per_delivery")
    private Integer salaryPerDelivery;

    @Column(name = "salary_per_hour")
    private Integer salaryPerHour;

    @Column(name = "salary_per_km")
    private Integer salaryPerKm;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "salary")
    private List<EmployeeSalary> employeeSalaries;
}
