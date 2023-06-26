package dodomu.deliverymanager.client_price;

import dodomu.deliverymanager.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClientPriceService {
    private final ClientPriceRepository clientPriceRepository;

    public List<ClientPrice> getAll() {
        return clientPriceRepository.findAll();
    }

    public ClientPrice addOrUpdate(ClientPrice clientPrice) {
        return clientPriceRepository.save(clientPrice);
    }

    public void deleteById(ClientPriceId id) {
        clientPriceRepository.deleteById(id);
    }

    public ClientPrice getById(ClientPriceId id) {
        return clientPriceRepository.findById(id).get();
    }

    public ClientPrice findClientPriceByClientAndDate(Client client, Date endDate) {
        return clientPriceRepository.findClientPriceByClientAndDate(client, endDate);
    }

    public ClientPrice findByClientIdAndPriceIdAndDate(Integer clientId, Integer priceId, Date date) {
        return clientPriceRepository.findByClientIdAndPriceIdAndDate(clientId, priceId, date);
    }
}
