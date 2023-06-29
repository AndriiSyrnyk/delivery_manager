package dodomu.deliverymanager.employee_schedule;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeScheduleId {
    private Integer employeeId;

    private String yearMonth;
}
