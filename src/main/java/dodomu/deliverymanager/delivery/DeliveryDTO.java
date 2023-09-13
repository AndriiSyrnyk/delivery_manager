package dodomu.deliverymanager.delivery;

import lombok.Data;

@Data
public class DeliveryDTO {
    private String clientName;

    private String localityName;

    private String streetName;
}