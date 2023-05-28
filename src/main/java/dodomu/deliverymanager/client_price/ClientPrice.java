package dodomu.deliverymanager.client_price;

import dodomu.deliverymanager.client.Client;
import dodomu.deliverymanager.price.Price;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@Table(name = "client_price")
@Entity
public class ClientPrice {
//    @Column(name = "client_id")
//    @ToString.Exclude
//    private int clientId;
//
//    @Column(name = "price_id")
//    @ToString.Exclude
//    private int priceId;
//
//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    private Client client;
//
//    @ManyToOne
//    @JoinColumn(name = "price_id")
//    private Price price;

    @EmbeddedId
    private ClientPriceId id;

    @ManyToOne
    @MapsId("clientId")
    private Client client;

    @ManyToOne
    @MapsId("priceId")
    private Price price;

    private Date date;
}
