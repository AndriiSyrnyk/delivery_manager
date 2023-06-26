package dodomu.deliverymanager.client;

import dodomu.deliverymanager.price.Price;
import lombok.Data;

@Data
public class ClientDTO {
    public ClientDTO() {
    }

    public ClientDTO(Client client, Price currentPrice) {
        this.id = client.getId();
        this.name = client.getName();
        this.comment = client.getComment();
        this.currentPrice = currentPrice;
    }

    private Integer id;

    private String name;

    private String comment;

    private Price currentPrice;
}
