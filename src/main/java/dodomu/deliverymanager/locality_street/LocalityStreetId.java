package dodomu.deliverymanager.locality_street;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class LocalityStreetId implements Serializable {
    private Integer localityId;
    private Integer streetId;
}
