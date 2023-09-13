package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.delivery.Delivery;
import dodomu.deliverymanager.delivery.DeliveryDTO;
import dodomu.deliverymanager.employee_salary.EmployeeSalaryService;
import dodomu.deliverymanager.salary.Salary;
import dodomu.deliverymanager.street.Street;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class EmployeeConverter {
    private final EmployeeSalaryService employeeSalaryService;

    public List<EmployeeDTO> convertEmployeeListToEmployeeDTOList(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee : employees) {
            final Salary currentSalary = employeeSalaryService
                    .findEmployeeSalariesByEmployeeAndDate(employee, new Date(System.currentTimeMillis()))
                    .getSalary();
            employeeDTOS.add(new EmployeeDTO(employee, currentSalary));
        }

        return employeeDTOS;
    }

    public EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee) {
        return new EmployeeDTO(employee, employeeSalaryService
                .findEmployeeSalariesByEmployeeAndDate(employee, new Date(System.currentTimeMillis()))
                .getSalary());
    }

    public List<EmployeeDeliveries> convertEmployeeListToEmployeeDeliveriesList(List<Employee> employees, Date date) {
        List<EmployeeDeliveries> employeeDeliveries = new ArrayList<>();

        for (Employee employee : employees) {
            List<Delivery> filteredDeliveries = employee.getDeliveries().stream()
                    .filter(delivery -> delivery.getDate().equals(date))
                    .sorted(Comparator.comparingInt(Delivery::getId).reversed())
                    .limit(7)
                    .sorted(Comparator.comparingInt(Delivery::getId))
                    .collect(Collectors.toList());

            EmployeeDeliveries employeeDelivery = new EmployeeDeliveries();
            employeeDelivery.setId(employee.getId());
            employeeDelivery.setName(employee.getName());
            employeeDelivery.setDeliveries(convertDeliveryListToDeliveryDTOList(filteredDeliveries));
            employeeDeliveries.add(employeeDelivery);
        }

        return employeeDeliveries;
    }

    private List<DeliveryDTO> convertDeliveryListToDeliveryDTOList(List<Delivery> deliveries) {
        List <DeliveryDTO> deliveryDTOS = new ArrayList<>();

        for (Delivery delivery : deliveries) {
            DeliveryDTO deliveryDTO = new DeliveryDTO();
            deliveryDTO.setClientName(delivery.getClient().getName());
            deliveryDTO.setLocalityName(delivery.getLocality().getName());
            Street street = delivery.getStreet();
            deliveryDTO.setStreetName(street != null ? street.getName() : "");
            deliveryDTOS.add(deliveryDTO);
        }

        return deliveryDTOS;
    }

    private boolean isDeliveryOnDate(Delivery delivery, Date date) {
        return delivery.getDate().equals(date);
    }

}
