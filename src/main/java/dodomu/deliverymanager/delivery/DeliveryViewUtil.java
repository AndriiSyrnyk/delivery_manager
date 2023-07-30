package dodomu.deliverymanager.delivery;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.locality.Locality;
import dodomu.deliverymanager.locality.LocalityService;
import dodomu.deliverymanager.schedule.Schedule;
import dodomu.deliverymanager.street.Street;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class DeliveryViewUtil {
    public static List<Employee> getEmployeesFromSchedules(List<Schedule> schedules) {
        List<Employee> employees = new ArrayList<>();

        for (Schedule schedule : schedules) {
            employees.add(schedule.getEmployee());
        }

        return employees;
    }

    public static List<Delivery> createDefault100Deliveries(Date date, Locality localityWithMinId) {
        List<Delivery> listOf100Deliveries = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Delivery delivery = new Delivery();
            delivery.setDate(date);
            if (localityWithMinId != null) {
                delivery.setLocality(localityWithMinId);
            }
            listOf100Deliveries.add(delivery);
        }

        return listOf100Deliveries;
    }

    public static Map<Integer, Locality> convertLocalityListToLocalityMap(List<Locality> localityList) {
        Map<Integer, Locality> localitiesMap = new HashMap<>();

        for (Locality locality : localityList) {
            localitiesMap.put(locality.getId(), locality);
        }

        return localitiesMap;
    }
}
