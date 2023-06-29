package dodomu.deliverymanager.schedule;

import dodomu.deliverymanager.employee.EmployeeService;
import dodomu.deliverymanager.utils.DateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ModelAndView getAll(@RequestParam(required = false) String yearMonth) {
        if (yearMonth == null) yearMonth = DateTimeUtil.getCurrentYearMonth();
        ModelAndView result = new ModelAndView("schedule/list");
        return result;
    }
}
