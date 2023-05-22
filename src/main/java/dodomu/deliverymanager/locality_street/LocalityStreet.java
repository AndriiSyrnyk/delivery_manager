package dodomu.deliverymanager.locality_street;

import dodomu.deliverymanager.locality.Locality;
import dodomu.deliverymanager.street.Street;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "locality_street")
public class LocalityStreet {
    @EmbeddedId
    private LocalityStreetId id;

    @ManyToOne
    @MapsId("localityId")
    private Locality locality;

    @ManyToOne
    @MapsId("streetId")
    private Street street;
}
