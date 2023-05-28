package dodomu.deliverymanager.date_employee;

import jakarta.persistence.Embeddable;

import java.sql.Date;

@Embeddable
public class DateEmployeeId {
    private Integer employeeId;
    private Date date;
}
