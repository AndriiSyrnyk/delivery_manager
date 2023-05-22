package dodomu.deliverymanager.client_price;

import jakarta.persistence.Embeddable;

@Embeddable
public class ClientPriceId {
    private Integer clientId;
    private Integer priceId;
}
