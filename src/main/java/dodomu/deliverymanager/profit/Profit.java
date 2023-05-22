package dodomu.deliverymanager.profit;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
public class Profit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    private Integer value;

    private Date date;
}
