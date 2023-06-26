package dodomu.deliverymanager.client_price;

import dodomu.deliverymanager.client.Client;
import dodomu.deliverymanager.price.Price;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
public class ClientPrice {
    public ClientPrice(Client client, Price price, Date date) {
        this.id = new ClientPriceId(client.getId(), price.getId(), date);
        this.client = client;
        this.price = price;
        this.date = date;
    }

    @EmbeddedId
    private ClientPriceId id;

    @ManyToOne
    @MapsId("clientId")
    private Client client;

    @ManyToOne
    @MapsId("priceId")
    private Price price;

    @Column(insertable = false, updatable = false)
    private Date date;
}
