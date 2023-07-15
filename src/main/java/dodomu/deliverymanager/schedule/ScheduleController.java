package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.Employee;
import dodomu.deliverymanager.employee.EmployeeService;
import dodomu.deliverymanager.employee_schedule.EmployeeSchedule;
import dodomu.deliverymanager.employee_schedule.EmployeeScheduleService;
import dodomu.deliverymanager.employee_schedule.EmployeeScheduleUtil;
import dodomu.deliverymanager.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final EmployeeScheduleService employeeScheduleService;

    private final EmployeeService employeeService;
    private final EmployeeScheduleUtil employeeScheduleUtil;

    @GetMapping("/list")
    public ModelAndView getAll(@RequestParam(required = false) String yearMonth) {
        if (yearMonth == null) yearMonth = DateTimeUtil.getCurrentYearMonth();
        final List<Date> datesByYearMonth = DateTimeUtil.getDatesByYearMonth(yearMonth);
        final List<EmployeeSchedule> employeeScheduleList = employeeScheduleService.getByYearMonth(yearMonth);
        final Date firstDate = DateTimeUtil.getFirstDateOfYearMonth(yearMonth);
        final Date lastDate = DateTimeUtil.getLastDateOfYearMonth(yearMonth);
        final List<Schedule> scheduleList = scheduleService.findByDateBetween(firstDate, lastDate);
        Collections.sort(employeeScheduleList, Comparator.comparing(EmployeeSchedule::getEmployeePriority));
        final SortedMap<Date, List<ScheduleDTO>> scheduleMap = ScheduleUtil.createSchedule(datesByYearMonth, employeeScheduleList, scheduleList);

        ModelAndView result = new ModelAndView("schedule/list");
        result.addObject("month", yearMonth);
        result.addObject("dateList", datesByYearMonth);
        result.addObject("employeeList", employeeScheduleList);
        result.addObject("scheduleMap", scheduleMap);
        return result;
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer employeeId, Date date) {
        ModelAndView result = new ModelAndView("schedule/edit");
        ScheduleId scheduleId = new ScheduleId(employeeId, date);
        Optional<Schedule> scheduleById = scheduleService.getById(scheduleId);
        final Employee employee = employeeService.getById(employeeId);
        Schedule schedule = scheduleById.isEmpty() ? new Schedule(scheduleId, employee) : scheduleById.get();

        result.addObject("schedule", schedule);

        return result;
    }

    @PostMapping("/edit")
    public String edit(Integer employeeId, Date dateParam, String startTime, String endTime, Integer amountPaid) {
        Employee employee = employeeService.getById(employeeId);
        Schedule schedule = ScheduleUtil.scheduleDTOParametersToSchedule(employee, dateParam, startTime, endTime, amountPaid);
        scheduleService.addOrUpdate(schedule);
        return "redirect:/schedule/list";
    }

    @GetMapping("/editPriority")
    public String editPriority(Integer employeeToSwapId, String employeeYearMonth, String arrowButton) {
        final List<EmployeeSchedule> employeeSchedulesToSwap = employeeScheduleUtil.changeEmployeePriority(employeeToSwapId, employeeYearMonth, arrowButton);

        if (employeeSchedulesToSwap.size() == 2) {
            employeeScheduleService.addOrUpdateAll(employeeSchedulesToSwap);
        }

        return "redirect:/schedule/list";
    }


}
