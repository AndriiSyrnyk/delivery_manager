package dodomu.deliverymanager.locality;

import dodomu.deliverymanager.locality_street.LocalityStreet;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

@Data
@Entity
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "locality")
    private Set<LocalityStreet> localityStreets;
}
