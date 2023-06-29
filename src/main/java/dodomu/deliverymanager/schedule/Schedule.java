package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
public class Schedule {
    @EmbeddedId
    private ScheduleId id;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    @Column(insertable = false, updatable = false)
    private Date date;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "amount_paid")
    private Integer amountPaid;

    @Column(name = "employee_priority")
    private Integer employeePriority;
}