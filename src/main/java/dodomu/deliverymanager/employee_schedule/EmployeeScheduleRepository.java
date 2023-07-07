package dodomu.deliverymanager.employee_schedule;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, EmployeeScheduleId> {
    @Query("SELECT es FROM EmployeeSchedule es WHERE es.yearMonth = :yearMonth")
    List<EmployeeSchedule> getByYearMonth(@Param("yearMonth") String yearMonth);

    @Query("SELECT COALESCE(MAX(es.employeePriority), 0) FROM EmployeeSchedule es")
    Integer getMaxEmployeePriority();

    @Transactional
    @Modifying
    @Query("DELETE FROM EmployeeSchedule es WHERE es.id NOT IN (:ids)")
    void deleteAllByIdNotIn(@Param("ids") List<EmployeeScheduleId> ids);

}
