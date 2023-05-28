package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.client.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("employee/list");
        // need to sort by deliveries count in future
        final List<Employee> allEmployee = employeeService.getAll();
        Collections.sort(allEmployee, Comparator.comparing(Employee::getId));
        // ...
        result.addObject("employeeList", allEmployee);
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee) {
        employeeService.addOrUpdate(employee);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer id) {
        ModelAndView result = new ModelAndView("employee/edit");
        result.addObject("employee", employeeService.getById(id));
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Employee employee) {
        employeeService.addOrUpdate(employee);
        return "redirect:/employee/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }
}
