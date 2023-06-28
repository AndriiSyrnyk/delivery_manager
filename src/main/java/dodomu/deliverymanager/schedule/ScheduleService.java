package dodomu.deliverymanager.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> getAll() {
        return scheduleRepository.findAll();
    }

    public Schedule addOrUpdate(Schedule employeeSalary) {
        return scheduleRepository.save(employeeSalary);
    }

    public void deleteById(ScheduleId id) {
        scheduleRepository.deleteById(id);
    }

    public Schedule getById(ScheduleId id) {
        return scheduleRepository.findById(id).get();
    }
}
