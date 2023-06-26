package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.salary.Salary;
import lombok.Data;

@Data
public class EmployeeDTO {
    public EmployeeDTO() {
    }

    public EmployeeDTO(Employee employee, Salary currentSalary) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.comment = employee.getComment();
        this.currentSalary = currentSalary;
    }

    private Integer id;

    private String name;

    private String comment;

    private Salary currentSalary;
}