package dodomu.deliverymanager.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, ScheduleId> {
    List<Schedule> findByDateBetween(Date firstDate, Date lastDate);
}
