package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.utils.DateTimeUtil;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class EmployeeSchedule {
    public EmployeeSchedule(Employee employee, String yearMonth, Integer employeePriority) {
        this.id = new EmployeeScheduleId(employee.getId(), yearMonth);
        this.employee = employee;
        this.yearMonth = yearMonth;
        this.employeePriority = employeePriority;
    }

    public EmployeeSchedule(Employee employee, Integer employeePriority) {
        this.id = new EmployeeScheduleId(employee.getId(), DateTimeUtil.getCurrentYearMonth());
        this.employee = employee;
        this.yearMonth = DateTimeUtil.getCurrentYearMonth();
        this.employeePriority = employeePriority;
    }

    @EmbeddedId
    private EmployeeScheduleId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @Column(insertable = false, updatable = false)
    private String yearMonth;

    @Column(name = "employee_priority")
    private Integer employeePriority;
}
