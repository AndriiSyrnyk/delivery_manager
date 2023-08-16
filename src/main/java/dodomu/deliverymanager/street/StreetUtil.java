package dodomu.deliverymanager.street;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class StreetUtil {
    public static Street getStreetByName(List<Street> streets, String streetName) {
        for (Street street : streets) {
            if (street.getName().equals(streetName)) {
                return street;
            }
        }

        return null;
    }
}
