package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class ScheduleDTO {
    public ScheduleDTO(Schedule schedule) {
        this.id = schedule.getId();
        this.employee = schedule.getEmployee();
        this.date = schedule.getDate();
        this.startTime = schedule.getStartTime().toString();
        this.endTime = schedule.getEndTime().toString();
        this.amountPaid = schedule.getAmountPaid().toString();
    }

    public ScheduleDTO(ScheduleId id, Employee employee, Date date) {
        this.id = id;
        this.employee = employee;
        this.date = date;
    }

    private ScheduleId id;

    private Employee employee;

    private Date date;

    private String startTime = "";

    private String endTime = "";

    private String amountPaid = "";
}
