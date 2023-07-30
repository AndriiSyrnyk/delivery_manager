package dodomu.deliverymanager.locality;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalityRepository extends JpaRepository<Locality, Integer> {
    Locality findTopByOrderByIdAsc();
}
