package dodomu.deliverymanager.employee_salary;

import dodomu.deliverymanager.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalary, EmployeeSalaryId> {
    @Query("SELECT es FROM EmployeeSalary es WHERE es.employee = :employee AND es.date <= :endDate AND " +
            "es.date = (SELECT MAX(es2.date) FROM EmployeeSalary es2 WHERE es2.employee = :employee AND es2.date <= :endDate)")
    EmployeeSalary findEmployeeSalariesByEmployeeAndDate(Employee employee, Date endDate);

    EmployeeSalary findByEmployeeIdAndSalaryIdAndDate(Integer employeeId, Integer salaryId, Date date);
}
