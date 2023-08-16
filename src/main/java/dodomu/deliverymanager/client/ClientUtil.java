package dodomu.deliverymanager.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientUtil {
    public static Client getClientByName(List<Client> clients, String clientName) {
        for (Client client : clients) {
            if (client.getName().equals(clientName)) {
                return client;
            }
        }

        return null;
    }
}
