package dodomu.deliverymanager.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/schedule")
@Controller
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("employee/list");
        return result;
    }
}
