package dodomu.deliverymanager.schedule;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleId {
    private Integer employeeId;
    private Date date;
}