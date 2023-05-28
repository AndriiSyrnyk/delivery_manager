package dodomu.deliverymanager.locality;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocalityService {
    private final LocalityRepository localityRepository;

    public List<Locality> getAll() {
        return localityRepository.findAll();
    }

    public Locality addOrUpdate(Locality locality) {
        return localityRepository.save(locality);
    }

    public void deleteById(Integer id) {
        localityRepository.deleteById(id);
    }

    public Locality getById(Integer id) {
        return localityRepository.findById(id).get();
    }
}
