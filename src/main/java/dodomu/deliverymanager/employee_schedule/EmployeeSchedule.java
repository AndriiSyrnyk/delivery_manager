package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class EmployeeSchedule {
    @EmbeddedId
    private EmployeeScheduleId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @Column(insertable = false, updatable = false)
    private String yearMonth;
}
