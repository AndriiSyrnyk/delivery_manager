package dodomu.deliverymanager.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public List<Schedule> findByDateBetween(Date firstDate, Date lastDate) {
        return scheduleRepository.findByDateBetween(firstDate, lastDate);
    }

    public Schedule addOrUpdate(Schedule employeeSalary) {
        return scheduleRepository.save(employeeSalary);
    }

    public void deleteById(ScheduleId id) {
        scheduleRepository.deleteById(id);
    }

    public Optional<Schedule> getById(ScheduleId id) {
        return scheduleRepository.findById(id);
    }
}
