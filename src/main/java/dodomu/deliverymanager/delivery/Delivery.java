package dodomu.deliverymanager.delivery;

import dodomu.deliverymanager.client.Client;
import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.locality.Locality;
import dodomu.deliverymanager.street.Street;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Date;

@Data
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="locality_id")
    private Locality locality;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="street_id")
    private Street street;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name="employee_id")
    private Employee employee;

    private Date date;

    @Column(name = "creation_time")
    private String creationTime;

    @Column(name = "ready_time")
    private String readyTime;

    @Column(name = "delivery_time")
    private String deliveryTime;
}
