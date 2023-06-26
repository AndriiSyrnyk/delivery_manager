package dodomu.deliverymanager.client_price;

import dodomu.deliverymanager.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface ClientPriceRepository extends JpaRepository<ClientPrice, ClientPriceId> {
    @Query("SELECT es FROM ClientPrice es WHERE es.client = :client AND es.date <= :endDate AND " +
            "es.date = (SELECT MAX(es2.date) FROM ClientPrice es2 WHERE es2.client = :client AND es2.date <= :endDate)")
    ClientPrice findClientPriceByClientAndDate(Client client, Date endDate);

    ClientPrice findByClientIdAndPriceIdAndDate(Integer clientId, Integer priceId, Date date);
}
