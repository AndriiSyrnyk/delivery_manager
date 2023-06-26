package dodomu.deliverymanager.client_price;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPriceId {
    private Integer clientId;
    private Integer priceId;
    private Date date;
}
