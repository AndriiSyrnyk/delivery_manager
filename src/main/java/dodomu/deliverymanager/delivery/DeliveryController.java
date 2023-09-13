package dodomu.deliverymanager.delivery;

import dodomu.deliverymanager.client.Client;
import dodomu.deliverymanager.client.ClientService;
import dodomu.deliverymanager.client.ClientUtil;
import dodomu.deliverymanager.employee.*;
import dodomu.deliverymanager.locality.Locality;
import dodomu.deliverymanager.locality.LocalityService;
import dodomu.deliverymanager.locality.LocalityUtil;
import dodomu.deliverymanager.schedule.ScheduleService;
import dodomu.deliverymanager.street.Street;
import dodomu.deliverymanager.street.StreetService;
import dodomu.deliverymanager.street.StreetUtil;
import dodomu.deliverymanager.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.sql.Time;
import java.util.*;

import static dodomu.deliverymanager.utils.DateTimeUtil.stringTimeToTime;

@RequiredArgsConstructor
@RequestMapping("/delivery")
@Controller
public class DeliveryController {
    private final DeliveryService deliveryService;

    private final ClientService clientService;

    private final ScheduleService scheduleService;

    private final StreetService streetService;

    private final LocalityService localityService;

    private final EmployeeService employeeService;

    private final EmployeeConverter employeeConverter;

    @GetMapping("/list")
    public ModelAndView getAll(@RequestParam(required = false) Date date) {
        if (date == null) date = DateTimeUtil.getCurrentDate();

        final List<Delivery> deliveries = deliveryService.findByDate(date);
        final List<Client> clients = clientService.getAll();
        final List<Employee> employees = DeliveryViewUtil.getEmployeesFromSchedules(scheduleService.findByDate(date));
        final List<Street> streets = streetService.getAll();
        final List<Locality> localities = localityService.getAll();
        Collections.sort(deliveries, Comparator.comparing(Delivery::getId));
        final Map<Integer, Locality> localitiesMap = DeliveryViewUtil.convertLocalityListToLocalityMap(localities);
        final List<EmployeeDeliveries> employeeDeliveries = employeeConverter.convertEmployeeListToEmployeeDeliveriesList(employees, date);

        ModelAndView result = new ModelAndView("delivery/list");
        result.addObject("date", date);
        result.addObject("deliveries", deliveries);
        result.addObject("clients", clients);
        result.addObject("employees", employees);
        result.addObject("streets", streets);
        result.addObject("localities", localities);
        result.addObject("localitiesMap", localitiesMap);
        result.addObject("employeeDeliveries", employeeDeliveries);

        return result;
    }

    @GetMapping("/add100")
    public String add(Date date) {
        final Locality localityWithMinId = localityService.getRecordWithMinId();
        final List<Delivery> default100Deliveries = DeliveryViewUtil.createDefault100Deliveries(date, localityWithMinId);
        deliveryService.addOrUpdateAll(default100Deliveries);
        return "redirect:/delivery/list?date=" + date.toString();
    }

    @PostMapping("/editClient")
    public ResponseEntity<String> editClient(Integer deliveryId, String value, String creationTime) {
        Delivery delivery = deliveryService.getById(deliveryId);
        Client client = ClientUtil.getClientByName(clientService.getAll(), value);
        final Time time = Objects.equals(creationTime, "") ? null : stringTimeToTime(creationTime);
        delivery.setCreationTime(time);
        delivery.setClient(client);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editLocality")
    public ResponseEntity<String> editLocality(Integer deliveryId, String value) {
        Delivery delivery = deliveryService.getById(deliveryId);
        Locality locality = LocalityUtil.getLocalityByName(localityService.getAll(), value);
        delivery.setLocality(locality);
        delivery.setStreet(null);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editStreet")
    public ResponseEntity<String> editStreet(Integer deliveryId, String value) {
        Delivery delivery = deliveryService.getById(deliveryId);
        Street street = StreetUtil.getStreetByName(streetService.getAll(), value);
        delivery.setStreet(street);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editCreationTime")
    public ResponseEntity<String> editCreationTime(Integer deliveryId, String value) {
        Delivery delivery = deliveryService.getById(deliveryId);
        final Time time = Objects.equals(value, "") ? null : stringTimeToTime(value);
        delivery.setCreationTime(time);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editReadyTime")
    public ResponseEntity<String> editReadyTime(Integer deliveryId, String value) {
        Delivery delivery = deliveryService.getById(deliveryId);
        final Time time = Objects.equals(value, "") ? null : stringTimeToTime(value);
        delivery.setReadyTime(time);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editDeliveryTime")
    public ResponseEntity<String> editDeliveryTime(Integer deliveryId, String value) {
        Delivery delivery = deliveryService.getById(deliveryId);
        final Time time = Objects.equals(value, "") ? null : stringTimeToTime(value);
        delivery.setDeliveryTime(time);
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }

    @PostMapping("/editEmployee")
    public ResponseEntity<List<EmployeeDeliveries>> editEmployee(Integer deliveryId, String value, Date date) {
        Delivery delivery = deliveryService.getById(deliveryId);
        Employee employee = EmployeeUtil.getEmployeeByName(employeeService.getAll(), value);
        delivery.setEmployee(employee);
        deliveryService.addOrUpdate(delivery);
        final List<Employee> employees = DeliveryViewUtil.getEmployeesFromSchedules(scheduleService.findByDate(date));
        final List<EmployeeDeliveries> employeeDeliveries = employeeConverter.convertEmployeeListToEmployeeDeliveriesList(employees, date);
        return ResponseEntity.ok(employeeDeliveries);
    }

    @PostMapping("/deleteAllDeliveriesFields")
    public ResponseEntity<String> deleteAllDeliveriesFields(Integer deliveryId) {
        Delivery delivery = deliveryService.getById(deliveryId);
        delivery.setClient(null);
        delivery.setCreationTime(null);
        delivery.setReadyTime(null);
        delivery.setDeliveryTime(null);
        delivery.setStreet(null);
        delivery.setEmployee(null);
        delivery.setLocality(localityService.getRecordWithMinId());
        deliveryService.addOrUpdate(delivery);
        return ResponseEntity.ok("Edit operation successful");
    }
}