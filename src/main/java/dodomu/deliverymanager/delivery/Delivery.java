package dodomu.deliverymanager.delivery;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "client_id")
    private int clientId;

    @Column(name = "locality_id")
    private int localityId;

    @Column(name = "street_id")
    private int streetId;

    @Column(name = "employee_id")
    private int employeeId;

    private Date date;

    @Column(name = "creation_time")
    private String creationTime;

    @Column(name = "ready_time")
    private String readyTime;

    @Column(name = "delivery_time")
    private String deliveryTime;
}
