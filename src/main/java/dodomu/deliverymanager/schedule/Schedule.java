package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Entity
@NoArgsConstructor
public class Schedule {
    public Schedule(ScheduleId id, Employee employee) {
        this.id = id;
        this.employee = employee;
        this.date = id.getDate();
    }

    public Schedule(ScheduleId id) {
        this.id = id;
    }

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
}