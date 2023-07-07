package dodomu.deliverymanager.employee_schedule;

import dodomu.deliverymanager.employee.EmployeeService;
import dodomu.deliverymanager.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/employee_schedule")
@Controller
public class EmployeeScheduleController {
    private final EmployeeScheduleService employeeScheduleService;
    private final EmployeeScheduleUtil employeeScheduleUtil;

    @GetMapping("/list")
    public ModelAndView getAll(@RequestParam(required = false) String yearMonth) {
        ModelAndView result = new ModelAndView("employee_schedule/list");
        if (yearMonth == null) yearMonth = DateTimeUtil.getCurrentYearMonth();
        result.addObject("employeeList", employeeScheduleUtil.employeeListToEmployeeDTOList(yearMonth));
        result.addObject("month", yearMonth);
        return result;
    }

    @PostMapping("/list")
    public String add(@RequestParam(name = "present", required = false) List<Integer> presentIds,
                      @RequestParam(name = "month") String yearMonth) {
        final List<EmployeeScheduleId> employeeScheduleIds = employeeScheduleUtil.employeeIdListToEmployeeScheduleIdList(presentIds, yearMonth);
        final List<EmployeeSchedule> listToSave = employeeScheduleUtil.createEmployeeScheduleToSaveList(presentIds, yearMonth);
        employeeScheduleService.deleteAllByIdNotIn(employeeScheduleIds);
        employeeScheduleService.addOrUpdateAll(listToSave);

        return "redirect:/schedule/list";
    }
}
