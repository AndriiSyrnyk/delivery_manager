package dodomu.deliverymanager.employee_salary;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaryId {
    private Integer employeeId;
    private Integer salaryId;
    private Date date;
}
