package dodomu.deliverymanager.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StreetService {
    private final StreetRepository streetRepository;

    public List<Street> getAll() {
        return streetRepository.findAll();
    }

    public Street addOrUpdate(Street street) {
        return streetRepository.save(street);
    }

    public void deleteById(Integer id) {
        streetRepository.deleteById(id);
    }

    public Street getById(Integer id) {
        return streetRepository.findById(id).get();
    }
}
