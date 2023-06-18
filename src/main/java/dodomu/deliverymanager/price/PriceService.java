package dodomu.deliverymanager.price;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PriceService {
    private final PriceRepository priceRepository;

    public List<Price> getAll() {
        return priceRepository.findAll();
    }

    public Price addOrUpdate(Price price) {
        return priceRepository.save(price);
    }

    public void deleteById(Integer id) {
        priceRepository.deleteById(id);
    }

    public Price getById(Integer id) {
        return priceRepository.findById(id).get();
    }
}
