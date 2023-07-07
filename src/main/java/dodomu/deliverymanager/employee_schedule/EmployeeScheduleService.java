package dodomu.deliverymanager.employee_schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeScheduleService {
    private final EmployeeScheduleRepository employeeScheduleRepository;

    public List<EmployeeSchedule> getByYearMonth(String yearMonth) {
        return employeeScheduleRepository.getByYearMonth(yearMonth);
    }

    public EmployeeSchedule addOrUpdate(EmployeeSchedule employeeSchedule) {
        return employeeScheduleRepository.save(employeeSchedule);
    }

    public List<EmployeeSchedule> addOrUpdateAll(List<EmployeeSchedule> employeeSchedules) {
        return employeeScheduleRepository.saveAll(employeeSchedules);
    }

    public void deleteById(EmployeeScheduleId id) {
        employeeScheduleRepository.deleteById(id);
    }

    public EmployeeSchedule getById(EmployeeScheduleId id) {
        return employeeScheduleRepository.findById(id).get();
    }

    public Integer getMaxEmployeePriority() {
        return employeeScheduleRepository.getMaxEmployeePriority();
    }

    public void deleteAllByIdNotIn(List<EmployeeScheduleId> ids) {
        employeeScheduleRepository.deleteAllByIdNotIn(ids);
    }
}