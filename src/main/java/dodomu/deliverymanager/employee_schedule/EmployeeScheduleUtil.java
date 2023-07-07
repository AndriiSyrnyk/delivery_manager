package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class EmployeeScheduleUtil {
    private final EmployeeScheduleService employeeScheduleService;

    private final EmployeeService employeeService;

    public List<EmployeeScheduleDTO> employeeListToEmployeeDTOList(String yearMonth) {
        List<EmployeeScheduleDTO> employeeScheduleDTOS = new ArrayList<>();
        List<EmployeeSchedule> employeeSchedules = employeeScheduleService.getByYearMonth(yearMonth);
        List<Employee> employeeList = employeeService.getAll();

        for (Employee employee : employeeList) {
            employeeScheduleDTOS.add(new EmployeeScheduleDTO(
                    employee, isEmployeePresent(employee, employeeSchedules)));
        }

        return employeeScheduleDTOS;
    }

    private static boolean isEmployeePresent(Employee employee, List<EmployeeSchedule> employeeScheduleList) {
        for (EmployeeSchedule employeeSchedule : employeeScheduleList) {
            if (employeeSchedule.getEmployee().equals(employee)) {
                return true;
            }
        }
        return false;
    }
    public List<EmployeeSchedule> createEmployeeScheduleToSaveList(List<Integer> employeesIds, String yearMonth) {
        List<EmployeeSchedule> employeeSchedules = new ArrayList<>();

        if (employeesIds != null) {
            List<EmployeeSchedule> oldEmployeeSchedules = employeeScheduleService.getByYearMonth(yearMonth);
            final Integer oldMaxPriority = getNextEmployeePriority(oldEmployeeSchedules);

            for (Integer id : employeesIds) {
                EmployeeScheduleId employeeScheduleId = new EmployeeScheduleId(id, yearMonth);
                EmployeeSchedule employeeSchedule;

                if (isEmployeeSchedulePresent(employeeScheduleId, oldEmployeeSchedules)) {
                    employeeSchedule = getEmployeeScheduleFromListById(employeeScheduleId, oldEmployeeSchedules);
                }
                else {
                    final Employee employee = employeeService.getById(id);
                    final Integer priority = Math.max(oldMaxPriority, getNextEmployeePriority(employeeSchedules));
                    employeeSchedule = new EmployeeSchedule(employee, yearMonth, priority);
                }

                employeeSchedules.add(employeeSchedule);
            }
        }

        return employeeSchedules;
    }

    private Integer getNextEmployeePriority(List<EmployeeSchedule> employeeSchedules) {
        Integer maxPriority = 0;

        for (EmployeeSchedule employeeSchedule : employeeSchedules) {
            final Integer employeePriority = employeeSchedule.getEmployeePriority();

            if (employeePriority > maxPriority) {
                maxPriority = employeePriority;
            }
        }

        return maxPriority + 1;
    }

    private EmployeeSchedule getEmployeeScheduleFromListById(EmployeeScheduleId employeeScheduleId, List<EmployeeSchedule> oldEmployeeSchedules) {
        for (EmployeeSchedule employeeSchedule : oldEmployeeSchedules) {
            if (employeeSchedule.getId().equals(employeeScheduleId)) {
                return employeeSchedule;
            }
        }
        return null;
    }

    private static Boolean isEmployeeSchedulePresent(EmployeeScheduleId employeeScheduleId, List<EmployeeSchedule> oldEmployeeSchedules) {
        for (EmployeeSchedule employeeSchedule : oldEmployeeSchedules) {
            if (employeeSchedule.getId().equals(employeeScheduleId)) {
                return true;
            }
        }
        return false;
    }
    public List<EmployeeScheduleId> employeeIdListToEmployeeScheduleIdList(List<Integer> presentIds, String yearMonth) {
        List<EmployeeScheduleId> employeeScheduleIds = new ArrayList<>();

        if (presentIds != null) {
            for (Integer id : presentIds) {
                employeeScheduleIds.add(new EmployeeScheduleId(id, yearMonth));
            }
        }
        else {
            employeeScheduleIds.add(new EmployeeScheduleId(0, yearMonth));
        }

        return employeeScheduleIds;
    }
}
