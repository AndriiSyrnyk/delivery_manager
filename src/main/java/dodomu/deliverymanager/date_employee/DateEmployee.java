package dodomu.deliverymanager.date_employee;

import dodomu.deliverymanager.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DateEmployee {
    @EmbeddedId
    private DateEmployeeId dateEmployeeId;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;

    private Integer value;
}
