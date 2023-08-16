package dodomu.deliverymanager.locality;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class LocalityUtil {
    public static Locality getLocalityByName(List<Locality> localities, String localityName) {
        for (Locality locality : localities) {
            if (locality.getName().equals(localityName)) {
                return locality;
            }
        }

        return null;
    }
}
