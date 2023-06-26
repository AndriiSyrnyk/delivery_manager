package dodomu.deliverymanager.employee_salary;

import dodomu.deliverymanager.employee.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeSalaryService {
    private final EmployeeSalaryRepository employeeSalaryRepository;

    public List<EmployeeSalary> getAll() {
        return employeeSalaryRepository.findAll();
    }

    public EmployeeSalary addOrUpdate(EmployeeSalary employeeSalary) {
        return employeeSalaryRepository.save(employeeSalary);
    }

    public void deleteById(EmployeeSalaryId id) {
        employeeSalaryRepository.deleteById(id);
    }

    public EmployeeSalary getById(EmployeeSalaryId id) {
        return employeeSalaryRepository.findById(id).get();
    }

    public EmployeeSalary findEmployeeSalariesByEmployeeAndDate(Employee employee, Date endDate) {
        return employeeSalaryRepository.findEmployeeSalariesByEmployeeAndDate(employee, endDate);
    }

    public EmployeeSalary getEmployeeSalaryByEmployeeIdSalaryIdAndDate(Integer employeeId, Integer salaryId, Date date) {
        return employeeSalaryRepository.findByEmployeeIdAndSalaryIdAndDate(employeeId, salaryId, date);
    }
}