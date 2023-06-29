package dodomu.deliverymanager.employee_schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeScheduleService {
    private final EmployeeScheduleRepository employeeScheduleRepository;

    public List<EmployeeSchedule> getAll() {
        return employeeScheduleRepository.findAll();
    }

    public EmployeeSchedule addOrUpdate(EmployeeSchedule employeeSalary) {
        return employeeScheduleRepository.save(employeeSalary);
    }

    public void deleteById(EmployeeScheduleId id) {
        employeeScheduleRepository.deleteById(id);
    }

    public EmployeeSchedule getById(EmployeeScheduleId id) {
        return employeeScheduleRepository.findById(id).get();
    }

}
