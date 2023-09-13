package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.delivery.DeliveryDTO;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDeliveries {
    private Integer id;

    private String name;

    private List<DeliveryDTO> deliveries;
}
