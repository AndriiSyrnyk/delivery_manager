package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.employee.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

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

    public List<EmployeeSchedule> changeEmployeePriority(Integer employeeId, String yearMonth, String arrowButton) {
        final List<EmployeeSchedule> employeeSchedulesByMonth = employeeScheduleService.getByYearMonth(yearMonth);
        List<EmployeeSchedule> employeeSchedulesToSwap = new ArrayList<>();
        Collections.sort(employeeSchedulesByMonth, Comparator.comparing(EmployeeSchedule::getEmployeePriority));
        EmployeeSchedule employeeSchedule1 = findEmployeeScheduleByEmployeeId(employeeId, employeeSchedulesByMonth);
        EmployeeSchedule employeeSchedule2 = findAdjacentEmployeeScheduleByArrowButton(employeeSchedule1, arrowButton, employeeSchedulesByMonth);

        if (employeeSchedule2 != null) {
            swapEmployeeSchedulePriority(employeeSchedule1, employeeSchedule2);
            employeeSchedulesToSwap.add(employeeSchedule1);
            employeeSchedulesToSwap.add(employeeSchedule2);
        }

        return employeeSchedulesToSwap;
    }
    private EmployeeSchedule findEmployeeScheduleByEmployeeId(Integer employeeId, List<EmployeeSchedule> employeeSchedules) {
        for (EmployeeSchedule employeeSchedule : employeeSchedules) {
            if (employeeSchedule.getEmployee().getId().equals(employeeId)) {
                return employeeSchedule;
            }
        }
        return null;
    }

    private EmployeeSchedule findAdjacentEmployeeScheduleByArrowButton(EmployeeSchedule employeeSchedule1,
                                                                       String arrowButton, List<EmployeeSchedule> employeeSchedulesByMonth) {
        final int employeeSchedule1Index = employeeSchedulesByMonth.indexOf(employeeSchedule1);
        final int employeeSchedule2Index = arrowButton.equals("right") ? employeeSchedule1Index + 1 : employeeSchedule1Index - 1;

        if (employeeSchedule2Index >= 0 && employeeSchedule2Index < employeeSchedulesByMonth.size()) {
            return employeeSchedulesByMonth.get(employeeSchedule2Index);
        }
        else {
            return null;
        }
    }

    private void swapEmployeeSchedulePriority(EmployeeSchedule employeeSchedule1, EmployeeSchedule employeeSchedule2) {
        final Integer employee1Priority = employeeSchedule1.getEmployeePriority();
        final Integer employee2Priority = employeeSchedule2.getEmployeePriority();
        employeeSchedule1.setEmployeePriority(employee2Priority);
        employeeSchedule2.setEmployeePriority(employee1Priority);
    }
}
