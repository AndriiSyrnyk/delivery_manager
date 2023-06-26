package dodomu.deliverymanager.client;

import dodomu.deliverymanager.client_price.ClientPriceService;
import dodomu.deliverymanager.price.Price;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientConverter {
    private final ClientPriceService clientPriceService;

    public List<ClientDTO> convertClientListToClientDTOList(List<Client> clients) {
        List<ClientDTO> clientDTOS = new ArrayList<>();

        for (Client client : clients) {
            final Price currentPrice = clientPriceService
                    .findClientPriceByClientAndDate(client, new Date(System.currentTimeMillis()))
                    .getPrice();
            clientDTOS.add(new ClientDTO(client, currentPrice));
        }

        return clientDTOS;
    }

    public ClientDTO convertClientToClientDTO(Client client) {
        return new ClientDTO(client, clientPriceService
                .findClientPriceByClientAndDate(client, new Date(System.currentTimeMillis()))
                .getPrice());
    }
}
