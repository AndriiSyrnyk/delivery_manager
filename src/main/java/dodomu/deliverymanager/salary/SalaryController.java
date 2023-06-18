package dodomu.deliverymanager.salary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RequestMapping("/salary")
@Controller
public class SalaryController {
    private final SalaryService salaryService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("salary/list");
        result.addObject("salaryList", salaryService.getAll());
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("salary", new Salary());
        return "salary/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Salary salary) {
        salaryService.addOrUpdate(salary);
        return "redirect:/salary/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("salary/edit");
        result.addObject("salary", salaryService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Salary salary) {
        salaryService.addOrUpdate(salary);
        return "redirect:/salary/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        salaryService.deleteById(id);
        return "redirect:/salary/list";
    }
}
