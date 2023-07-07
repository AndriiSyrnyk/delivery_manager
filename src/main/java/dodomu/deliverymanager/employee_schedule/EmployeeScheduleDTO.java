package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeScheduleDTO {
    public EmployeeScheduleDTO(Employee employee, Boolean present) {
        this.employee = employee;
        this.id = employee.getId();
        this.name = employee.getName();
        this.present = present;
    }
    private Employee employee;

    private Integer id;

    private String name;

    private Boolean present;
}
