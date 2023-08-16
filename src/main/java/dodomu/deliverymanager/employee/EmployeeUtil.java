package dodomu.deliverymanager.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeUtil {
    public static Employee getEmployeeByName(List<Employee> employees, String employeeName) {
        for (Employee employee : employees) {
            if (employee.getName().equals(employeeName)) {
                return employee;
            }
        }

        return null;
    }
}
