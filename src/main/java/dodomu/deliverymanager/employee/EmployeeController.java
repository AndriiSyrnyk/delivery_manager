package dodomu.deliverymanager.employee;

import dodomu.deliverymanager.employee_salary.EmployeeSalary;
import dodomu.deliverymanager.employee_salary.EmployeeSalaryId;
import dodomu.deliverymanager.employee_salary.EmployeeSalaryService;
import dodomu.deliverymanager.employee_schedule.EmployeeSchedule;
import dodomu.deliverymanager.employee_schedule.EmployeeScheduleService;
import dodomu.deliverymanager.salary.Salary;
import dodomu.deliverymanager.salary.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;
import java.util.*;

@RequiredArgsConstructor
@RequestMapping("/employee")
@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    private final SalaryService salaryService;

    private final EmployeeSalaryService employeeSalaryService;

    private final EmployeeConverter employeeConverter;

    private final EmployeeScheduleService employeeScheduleService;

    @GetMapping("/list")
    public ModelAndView getAll() {
        ModelAndView result = new ModelAndView("employee/list");
        // need to sort by deliveries count in future
        final List<Employee> allEmployee = employeeService.getAll();
        Collections.sort(allEmployee, Comparator.comparing(Employee::getId));

        result.addObject("employeeList", employeeConverter
                .convertEmployeeListToEmployeeDTOList(allEmployee));
        return result;
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("salaryList", salaryService.getAll());
        return "employee/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Employee employee, @ModelAttribute Salary salary) {
        final Employee createdEmployee = employeeService.addOrUpdate(employee);
        final EmployeeSalary employeeSalary = new EmployeeSalary(createdEmployee, salary, new java.sql.Date(System.currentTimeMillis()));
        employeeSalaryService.addOrUpdate(employeeSalary);
        final EmployeeSchedule employeeSchedule = new EmployeeSchedule(createdEmployee, employeeScheduleService.getMaxEmployeePriority());
        employeeScheduleService.addOrUpdate(employeeSchedule);
        return "redirect:/employee/list";
    }

    @GetMapping("/edit")
    public ModelAndView edit(Integer employeeId) {
        ModelAndView result = new ModelAndView("employee/edit");
        final EmployeeDTO employeeDTO = employeeConverter.convertEmployeeToEmployeeDTO(employeeService.getById(employeeId));
        result.addObject("employee", employeeDTO);
        result.addObject("salaryList", salaryService.getAll());
        result.addObject("currentSalary", employeeDTO.getCurrentSalary());
        return result;
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Employee employee, @ModelAttribute Salary salary, Integer employeeId) {
        employee.setId(employeeId);
        final Date today = new Date(System.currentTimeMillis());
        final Employee updatedEmployee = employeeService.addOrUpdate(employee);
        final Salary oldSalary = employeeSalaryService.findEmployeeSalariesByEmployeeAndDate(employee, today).getSalary();

        if (!oldSalary.equals(salary)) {
            final EmployeeSalary employeeSalary = new EmployeeSalary(updatedEmployee, salary, today);
            final EmployeeSalary todayEmployeeSalary = employeeSalaryService
                    .getEmployeeSalaryByEmployeeIdSalaryIdAndDate(employeeId, oldSalary.getId(), today);

            if (todayEmployeeSalary != null) {
                employeeSalaryService.deleteById(new EmployeeSalaryId(employeeId, oldSalary.getId(), today));
            }

            employeeSalaryService.addOrUpdate(employeeSalary);
        }
        return "redirect:/employee/list";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Integer id) {
        employeeService.deleteById(id);
        return "redirect:/employee/list";
    }
}
