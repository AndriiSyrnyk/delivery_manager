package dodomu.deliverymanager.locality;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dodomu.deliverymanager.delivery.Delivery;
import dodomu.deliverymanager.street.Street;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @JsonManagedReference
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "locality")
    private List<Street> streets;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "locality")
    private List<Delivery> deliveries;
}
