package dodomu.deliverymanager.delivery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;

    public List<Delivery> findByDate(Date date) {
        return deliveryRepository.findByDate(date);
    }

    public Delivery addOrUpdate(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    public List<Delivery> addOrUpdateAll(List<Delivery> deliveries) {
        return deliveryRepository.saveAll(deliveries);
    }

    public void deleteById(Integer id) {
        deliveryRepository.deleteById(id);
    }

    public Delivery getById(Integer id) {
        return deliveryRepository.findById(id).get();
    }
}
